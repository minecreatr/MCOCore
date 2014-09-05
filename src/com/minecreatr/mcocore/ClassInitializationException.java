package com.minecreatr.mcocore;

/**
 * Created on 9/2/2014
 *
 * @author minecreatr
 */
public class ClassInitializationException extends Exception{

    private Exception cause;

    public ClassInitializationException(Exception cause){
        this.cause=cause;
    }

    public Exception getCause(){
        return this.cause;
    }
}