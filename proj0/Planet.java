public class Planet{
	public double xxPos; // Current posistion x(Cartesian)
	public double yyPos;
	public double xxVel;// Its current velocity in the x direction
	public double yyVel;
	public double mass; //Its mass
	public String imgFileName; //The name of the file that corresponds to the image that depicts the planet 

	/** Two constructors */
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
	    imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double distance;
		distance =Math.sqrt(Math.pow((this.xxPos - p.xxPos), 2) + Math.pow((this.yyPos - p.yyPos), 2));
		return distance;
	}

	public double calcForceExertedBy(Planet p){
		double G = 6.67 * Math.pow(10, -11);
		double force;
		double r = this.calcDistance(p);
		force = G * this.mass * p.mass / Math.pow(r,2);
		return force;
	}

	public double calcForceExertedByX(Planet p){
		double fx;
		fx = this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
		return fx;
	}

	public double calcForceExertedByY(Planet p){
		double fy;
		fy = this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
		return fy;
	}

	public double calcNetForceExertedByX(Planet[] nBody){
		double netFx = 0; // remember to initialize
		for(Planet body : nBody){ // nBody is an array to receive multiple planet
			if(!this.equals(body)){
				netFx += this.calcForceExertedByX(body);
			}
		}
		return netFx;
	}

	public double calcNetForceExertedByY(Planet[] nBody){
		double netFy = 0;
		for(Planet body : nBody){
			if(!this.equals(body)){
				netFy += this.calcForceExertedByY(body);
			}
		}
		return netFy;
	}

	public void update(double t, double nx, double ny){
		double ax = nx / this.mass;
		double ay = ny / this.mass;
		this.xxVel += ax * t;
		this.yyVel += ay * t;
		this.xxPos += this.xxVel * t;
		this.yyPos += this.yyVel * t;
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName); // Draw current image
	}
}