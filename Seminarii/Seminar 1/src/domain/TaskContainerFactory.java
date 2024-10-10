package domain;

import java.util.Objects;
/*
    Aici avem 2 Design Patterns
    Factory Design si singleton Design pattern
    Ambele Creation Deseign Patterns
    Singleton=> o instanta
    Factory => o metoda de incapsula object creation
 */
public class TaskContainerFactory implements Factory {
    private static TaskContainerFactory instance = null;

    private TaskContainerFactory() {
    }



    public static synchronized TaskContainerFactory getInstance() {
        if (instance == null) {
            instance = new TaskContainerFactory();
            return instance;
        }
        return instance;
    }
    @Override
    public Container createContainer(String strategy) {
        if(Objects.equals(strategy.toLowerCase(), "fifo")){
            return new QueueContainer();
        }
        if(Objects.equals(strategy.toLowerCase(), "lifo")){
            return new StackContainer();
        }
        return null;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
