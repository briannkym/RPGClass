
public class monster {
	int monster_attack, max_monster_health;
	
	public monster(int attack, int health){
		this.monster_attack = attack;
		this.max_monster_health = health;
	}
	
	public void battleMonster(player p)
	{
		 int monster_health = max_monster_health;
		 System.out.println("A spider notices you!");
		 while(monster_health > 0)
		 {
					p.health -= (int)(monster_attack*Math.random());
					System.out.println("The spider hits you : " + p.health +"hp remaining!");
					if(p.health <=0)
					{
							  System.out.println("Game Over");
							  System.exit(0);
					}
					monster_health -= (int)(p.attack*Math.random());
					System.out.println("You bop the spider on its head.");
		 }
		 System.out.println("You have defeated the spider!");
	}
}
