package org.firstinspires.ftc.teamcode.Debugger;

/**
 * Created by Justin on 2/3/2018.
 */

public class debugMessage {

    private Exception error;
    private StackTraceElement stackTraceElement;
    private ErrorType errorType;

    public debugMessage(Exception error, ErrorType type){
        this.error = error;
        stackTraceElement = error.getStackTrace()[0];
        errorType = type;
    }

    public String getErrorName(){
        return error.getClass().getSimpleName();
    }

    public int getLineNumber(){
        return stackTraceElement.getLineNumber();
    }

    public String getMethodName(){
        return stackTraceElement.getMethodName();
    }

    @Override
    public String toString(){
        String result = "Error has occurred on line: " + getLineNumber() + " " + getMethodName() + " \n Error type: " + getErrorName();
        return result;
    }

}
