package es.juandavidvega.rpgcombat.props;


import es.juandavidvega.rpgcombat.character.Health;
import es.juandavidvega.rpgcombat.engine.events.EventBus;
import es.juandavidvega.rpgcombat.engine.events.Targetable;
import es.juandavidvega.rpgcombat.engine.events.props.DamagePropsEvent;
import rx.Observable;

import static es.juandavidvega.rpgcombat.engine.events.EventType.*;

public class House implements Targetable{

    private final Health health;

    public House(Health health) {
        this.health = health;
        listenDamages();
    }


    private void listenDamages() {
        getBus().<DamagePropsEvent>streamOf(DamageProps)
                .filter(this::IsThisTheTarget)
                .subscribe(this::receive);
    }

    private Boolean IsThisTheTarget(DamagePropsEvent damagePropsEvent) {
        return damagePropsEvent.house() == this;
    }


    public Health health() {
        return health;
    }

    private EventBus getBus() {
        return EventBus.get();
    }

    public void receive(DamagePropsEvent damagePropsEvent) {
        health.subtract(new Double(damagePropsEvent.damage()));
    }
}
