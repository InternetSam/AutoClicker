package com.internetsam.auto_click;

import java.awt.Robot;
import java.awt.event.InputEvent;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class Main implements NativeKeyListener{
	public static boolean isPressed = false;
	public static int x = 0;	
	
	
    public void nativeKeyPressed(NativeKeyEvent e) {
    	
            System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
            if (e.getKeyCode() == NativeKeyEvent.VC_END) {
                GlobalScreen.unregisterNativeHook();
                System.out.println("DONE!");
                System.exit(0);
            	
        }
            
        startRapidClick(e);
        
    }

    public void nativeKeyReleased(NativeKeyEvent e) {

    }

    public void nativeKeyTyped(final NativeKeyEvent e) {
        
    }
	 public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {

        	System.err.println("There was an error with JNativeHook");
            System.err.println(ex.getMessage());
            System.err.println("Exiting...");
            System.exit(1);
        }

        GlobalScreen.getInstance().addNativeKeyListener(new Main());
        
        
    }
    
    public void botCode() {
    	try {
	        Robot robot = new Robot();

	        while(true) {
	        	
	        	Thread.sleep(50);
	        	robot.mousePress(InputEvent.BUTTON1_MASK);
	        	
	        	robot.mouseRelease(InputEvent.BUTTON1_MASK);
		    	System.out.println(isPressed);
	        	x++;
	        	
	        	if(isPressed != true) {
	        		break;
	        	}
	        
	        }
        }catch(Exception e) {
 
        	
        }
 
    }
    
    public void startRapidClick(NativeKeyEvent e){
    	
    	if(e.getKeyCode() == NativeKeyEvent.VC_HOME && isPressed != true) {
        	
        	isPressed = true;
        	Thread t = new Thread(new Runnable() {
        		
				@Override
				public void run() {
	        		botCode();
					
				}
        	});
        	t.start();
        	
       }else if(e.getKeyCode() == NativeKeyEvent.VC_HOME && isPressed != false){
    	   
    	   isPressed = false;
    	   
       }
    }

	
}