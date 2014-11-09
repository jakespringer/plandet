package edu.catlin.springerj.explore.jake.enemy;

import edu.catlin.springerj.explore.jake.items.BulletRechargeComponent;
import edu.catlin.springerj.explore.planets.Planet;
import edu.catlin.springerj.explore.rory.CircleCollisionComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.view.EntityTargetComponent;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;

public class AIFlyingSineSystem extends AbstractSystem {

	private PositionComponent pc;
	private VelocityComponent vc;
	private EntityTargetComponent etc;
	private BulletRechargeComponent brc;
	
	@Override
	public void initialize(AbstractEntity e) {
		pc = e.get(PositionComponent.class);
		vc = e.get(VelocityComponent.class);
		etc = e.get(EntityTargetComponent.class);
		brc = e.get(BulletRechargeComponent.class);
	}

	@Override
	public void update() {
		vc.velocity = new Vector2(0.0d, 32.0d*Math.sin(Core.getDefaultTimer().getCurrentTime()*1.6d));
		brc.currentRecharge += Core.getDefaultTimer().getDeltaTime();
		
		if (brc.currentRecharge > brc.rechargeTime) {
			// initiate shoot cycle
			
			Vector2 pointontargetline = pc.position.subtract(etc.entity.getComponent(PositionComponent.class).position);
			
			for (AbstractEntity e : Core.getRootManager().getEntities()) {
				if (e instanceof Planet) {
					Planet p = (Planet) e;
					double radius = p.get(CircleCollisionComponent.class).size;
					Vector2 center = p.get(PositionComponent())
				}
			}
		}
	}

}