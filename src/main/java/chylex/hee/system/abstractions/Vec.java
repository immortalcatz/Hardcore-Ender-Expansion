package chylex.hee.system.abstractions;
import java.util.Random;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import chylex.hee.system.util.MathUtil;

public class Vec{
	public static Vec xyz(double x, double y, double z){
		return new Vec(x,y,z);
	}

	public static Vec xz(double x, double z){
		return new Vec(x,0D,z);
	}
	
	public static Vec zero(){
		return new Vec(0D,0D,0D);
	}
	
	public static Vec from(Vec3 vec3){
		return new Vec(vec3.xCoord,vec3.yCoord,vec3.zCoord);
	}
	
	public static Vec xzRandom(Random rand){
		return new Vec(rand.nextDouble()-0.5D,0D,rand.nextDouble()-0.5D).normalized();
	}
	
	public static Vec xyzRandom(Random rand){
		return new Vec(rand.nextDouble()-0.5D,rand.nextDouble()-0.5D,rand.nextDouble()-0.5D).normalized();
	}
	
	public double x, y, z;
	
	private Vec(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double length(){
		return Math.sqrt(x*x+y*y+z*z);
	}
	
	public Vec normalized(){
		double len = length();
		return MathUtil.floatEquals((float)len,0F) ? Vec.zero() : Vec.xyz(x/len,y/len,z/len);
	}
	
	public Vec offset(double offX, double offY, double offZ){
		return Vec.xyz(x+offX,y+offY,z+offZ);
	}
	
	public Vec offset(Vec byVec, double factor){
		return Vec.xyz(x+byVec.x*factor,y+byVec.y*factor,z+byVec.z*factor);
	}
	
	public double distance(Vec vec){
		return MathUtil.distance(vec.x-x,vec.y-y,vec.z-z);
	}
	
	public Pos toPos(){
		return Pos.at(x,y,z);
	}
	
	public Vec3 toVec3(){
		return Vec3.createVectorHelper(x,y,z);
	}
	
	public AxisAlignedBB toAABB(){
		return AxisAlignedBB.getBoundingBox(x,y,z,x,y,z);
	}
}
