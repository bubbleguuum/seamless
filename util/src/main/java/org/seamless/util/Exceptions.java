 /*
 * Copyright (C) 2012 4th Line GmbH, Switzerland
 *
 * The contents of this file are subject to the terms of either the GNU
 * Lesser General Public License Version 2 or later ("LGPL") or the
 * Common Development and Distribution License Version 1 or later
 * ("CDDL") (collectively, the "License"). You may not use this file
 * except in compliance with the License. See LICENSE.txt for more
 * information.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */
package org.seamless.util;


/**
 * @author Christian Bauer
 */
public class Exceptions {

    public static Throwable unwrap(Throwable throwable) throws IllegalArgumentException {
        if (throwable == null) {
            throw new IllegalArgumentException("Cannot unwrap null throwable");
        }
        for (Throwable current = throwable; current != null; current = current.getCause()) {
            throwable = current;
        }
        return throwable;
    }
    
    /*
      Useful in broad try...catch statements to not let unchecked exceptions be catched: 
     
      try {
          ....
	  } catch (Exception e) {
	  	throwIfUnchecked(e);
	  	...
	  }
    */
	public static void throwIfUnchecked(Exception e) throws RuntimeException {
		if(e instanceof RuntimeException) throw (RuntimeException)e;
	}
	
	public static void throwIfNPE(Exception e) throws NullPointerException {
		if(e instanceof NullPointerException) throw (NullPointerException)e;
	}

	public static void throwIfInterrupted() throws InterruptedException {
		if(Thread.interrupted()) {
			throw new InterruptedException();
		}
	}

}
