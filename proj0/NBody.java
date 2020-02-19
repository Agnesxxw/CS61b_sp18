public class NBody{
 /* Every time you call a read method from the In class,
   * it reads the next thing from the file, assuming it is
   * of the specified type. */

 /** return the radius */
 public static double readRadius(String fileName){
  In in = new In(fileName);
        int N = in.readInt(); // first line in the file is the number of planet.
        double Radius = in.readDouble(); //second line is the radius.
        return Radius;
 }

 /** read the planet information */
 public static Planet[] readPlanets(String fileName){ // return planet type string
  In in = new In(fileName);
  int num = in.readInt();
  in.readDouble();
  Planet[] p = new Planet[num]; // p has five elements, each is Planet type
  int i = 0; // NOT 1 !!!!!!!!!!!
  while(i < num){ // each Planet element have five characteristics
   double xp = in.readDouble();
   double yp = in.readDouble();
   double vx = in.readDouble();
   double vy = in.readDouble();
   double m = in.readDouble();
   String f = in.readString();
   p[i++] = new Planet(xp, yp, vx, vy, m, f);
   //p[i] =new Planet(xp, yp, vx, vy, m, f);
   //i++;
  }
  return p;
 }

 public static void main(String[] args){
   double T = Double.parseDouble(args[0]);
         double dt = Double.parseDouble(args[1]);
         String filename = args[2];
         double radius = NBody.readRadius(filename);
         Planet[] pl = NBody.readPlanets(filename);

   StdDraw.setScale(-radius, radius);
   StdDraw.clear(); // Clear the canvas to white
         StdDraw.picture(0, 0,"images/starfield.jpg");

         /** Draw each planet's position */
   /**Be sure to do this after drawing the starfield.jpg file so that the planets don’t get covered up by the background. */
   for(Planet p : pl){
    p.draw();
   }
   StdDraw.enableDoubleBuffering();//This is a graphics technique to prevent flickering in the animation.If don’t call this function, any attempt at smooth animation will look bad and flickery
   /** You can think of double buffering as collecting all of the lines, points, shapes, and text that you tell it to draw, and then drawing them all simultaneously, upon request */

   /** Annimatation duration is T, time step size is dt */
   for(double t = 0; t < T; t += dt){
    double[] xForces = new double[pl.length];
    double[] yForces = new double[pl.length];

    for(int i = 0; i < pl.length; i++){
     xForces[i] = pl[i].calcNetForceExertedByX(pl);
     yForces[i] = pl[i].calcNetForceExertedByY(pl);
    }

    /** Call update on each of the planets. This will update each planet’s position, velocity, and acceleration. */
    /** do not make any calls to update until all forces have been calculated and safely stored in xForces and yForces */
    for(int j = 0; j < pl.length; j++){
     pl[j].update(dt, xForces[j], yForces[j]);
    }

    StdDraw.picture(0, 0,"images/starfield.jpg");

    for(Planet p : pl){
     p.draw();
    }
    StdDraw.show();
             StdDraw.pause(10);
   }

     }


}