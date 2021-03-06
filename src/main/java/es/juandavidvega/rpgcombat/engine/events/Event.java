package es.juandavidvega.rpgcombat.engine.events;

public abstract class Event {
    public abstract Targetable target();
    public abstract EventType type();

    public void publishOn(){
        EventBus.get().send(this);
    }

    public boolean is(EventType type) {
        return type.equals(type());
    }
}
