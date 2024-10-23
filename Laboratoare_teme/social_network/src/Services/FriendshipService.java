package Services;

import Domain.Entity;
import Domain.Friendship;
import Domain.Tuple;
import Domain.User;
import Repository.Repository;

import java.util.*;

public class FriendshipService extends EntityService<Tuple<Long,Long>, Friendship> {
    UserService userService;
    public FriendshipService(Repository<Tuple<Long,Long>, Friendship> repository,UserService userService) {
        super(repository);
        this.userService = userService;
    }

    private static void createGraph(List<Friendship> friendships, Map<Long, List<Long>> graph) {
        if (friendships == null || friendships.isEmpty()) return;
        for (Friendship friendship : friendships) {
            Long person1 = friendship.getId().getE1();
            Long person2 = friendship.getId().getE2();
            addEdge(person1, person2, graph);
            addEdge(person2, person1, graph);
        }
    }

    private static void addEdge(Long source, Long destination, Map<Long, List<Long>> graph) {
        graph.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
    }

    private static void dfs(Long node, Set<Long> visited, Map<Long, List<Long>> graph, boolean saveComponent, List<Long> currentComponent) {
        visited.add(node);
        if (graph.containsKey(node))
            for (Long neighbor : graph.get(node))
                if (!visited.contains(neighbor))
                    dfs(neighbor, visited, graph, saveComponent, currentComponent);
        if (saveComponent)
            currentComponent.add(node);
    }
    public int getNumberOfCommunities() {
        List<Friendship> friendships = new ArrayList<>((Collection<Friendship>) getAll());
        return noConnectedComponents(friendships);
    }

    public String getMostSocialCommunity() {
        Map<Long, List<Long>> graph = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        List<Friendship> friendships = new ArrayList<>((Collection<Friendship>) getAll());
        createGraph(friendships, graph);
        List<Long> mostSocialCommunity = findLongestPath(graph);

        for (Long id : mostSocialCommunity) {
            User user = userService.getById(id);
            if (user != null) {
                stringBuilder.append(user).append(", ");
            }
        }
        return stringBuilder.toString();
    }
    private int noConnectedComponents(List<Friendship> friendships) {
        Map<Long, List<Long>> graph = new HashMap<>();
        createGraph(friendships, graph);
        Set<Long> visited = new HashSet<>();
        int components = 0;
        for (User user : userService.getAll()) {
            if (!graph.containsKey(user.getId()))
                components++;
        }
        for (Long node : graph.keySet())
            if (!visited.contains(node)) {
                components++;
                dfs(node, visited, graph, false, new ArrayList<>());
            }
        return components;
    }

    private List<Long> findLongestPath(Map<Long, List<Long>> graph) {
        List<Long> longestPath = new ArrayList<>();
        Set<Long> visited = new HashSet<>();
        for (Long node : graph.keySet()) {
            List<Long> currentPath = new ArrayList<>();
            dfsForLongestPath(node, visited, graph, currentPath, longestPath);
        }
        return longestPath;
    }

    private void dfsForLongestPath(Long node, Set<Long> visited, Map<Long, List<Long>> graph, List<Long> currentPath, List<Long> longestPath) {
        visited.add(node);
        currentPath.add(node);
        if (graph.containsKey(node))
            for (Long neighbor : graph.get(node))
                if (!visited.contains(neighbor))
                    dfsForLongestPath(neighbor, visited, graph, currentPath, longestPath);
        if (currentPath.size() > longestPath.size()) {
            longestPath.clear();
            longestPath.addAll(currentPath);
        }
        currentPath.remove(node);
    }
}
