package simpleUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import java.awt.Color;

import org.junit.Test;

import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;


public class Tester11 {

	@Test
	public void recursiveTest1(){
		
		Scene scene = new Scene();
		scene.set_distance(200);
		
		Sphere sphere = new Sphere(new Color(0,0, 100), 500, new Point3D(0.0, 0.0, -1000),new Material());	
		Material material = new Material();		
		material.set_nShininess(20); 
		material.set_kt(0.5);
		sphere.set_material(new Material(material));
		scene.addGeometry(sphere);
		
		Sphere sphere2 = new Sphere(new Color (100, 20, 20), 250, new Point3D(0.0, 0.0, -1000),new Material());
		material.set_kt(0);	
		sphere2.set_material(new Material(material));
		scene.addGeometry(sphere2);

		scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150), 
							   0.1, 0.00001, 0.000005,  new Vector(2, 2, -3))); // NOW
				
		ImageWriter imageWriter = new ImageWriter("Recursive Test1", 500, 500, 500, 500);
		
		Render render = new Render( scene, imageWriter);
		
		render.renderImage();
		render.printGrid(50);
		imageWriter.writeToimage();
		
	}
	
	
	
	@Test
	public void recursiveTest2(){
		
		Scene scene = new Scene();
		scene.set_distance(200);
		
		Sphere sphere = new Sphere(Color.RED, 500, new Point3D(0.0, 0.0, -1000),new Material());
		Material material = new Material();
		material.set_nShininess(20);
		material.set_kt(0.5);
		sphere.set_material(new Material(material));	
		scene.addGeometry(sphere);
		
		Sphere sphere2 = new Sphere(Color.BLUE, 250, new Point3D(0.0, 0.0, -1000),new Material());
		material.set_kt(0);
		sphere2.set_material(new Material(material));
		scene.addGeometry(sphere2);
		
		scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150), 
					    0.1, 0.00001, 0.000005, new Vector(2, 2, -3)));
			
		ImageWriter imageWriter = new ImageWriter("Recursive Test2", 500, 500, 500, 500);
		
		Render render = new Render(scene, imageWriter);
		
		render.renderImage();
		render.printGrid(50);
		imageWriter.writeToimage();
	}
	
	
	@Test
	public void recursiveTest3(){
		
		Scene scene = new Scene();
		scene.set_distance(200);
		Sphere sphere = new Sphere(new Color(0, 0, 100), 300, new Point3D(-550, -500, -1000),new Material());
		Material material = new Material();
		material.set_nShininess(20); 
		material.set_kt(0.5);
		sphere.set_material(new Material(material));
		scene.addGeometry(sphere);
		
		Sphere sphere2 = new Sphere(new Color(100, 20, 20), 150, new Point3D(-550, -500, -1000),new Material());
		Material material2 = new Material();
		material2.set_nShininess(20);
		material2.set_kt(0);		
		sphere2.set_material(new Material(material2));
		scene.addGeometry(sphere2);
		
		Triangle triangle = new Triangle(new Color(20, 20, 20), new Point3D(  1500, -1500, -1500),
				 new Point3D( -1500,  1500, -1500),
				 new Point3D(  200,  200, -375),new Material());

		Triangle triangle2 = new Triangle(new Color(20, 20, 20), new Point3D(  1500, -1500, -1500),
				  new Point3D( -1500,  1500, -1500),
				  new Point3D( -1500, -1500, -1500),new Material());

		
	
		Material material3 = new Material();		
		material3.set_kr(1);		
		triangle.set_material(new Material(material3));
		
		Material material4 = new Material();		
		material4.set_kr(0.5);		
		triangle2.set_material(new Material(material4));
		
		scene.addGeometry(triangle);
		scene.addGeometry(triangle2);


		scene.addLight(new SpotLight(new Color(255, 100, 100),  new Point3D(200, 200, -150), 
				   0.1, 0.00001, 0.000005,  new Vector(-2, -2, -3)));
	
		ImageWriter imageWriter = new ImageWriter("Recursive Test3", 500, 500, 500, 500);
		
		Render render = new Render( scene, imageWriter);
		render.renderImage();
		render.printGrid(50);
		imageWriter.writeToimage();		
	}
	

}

