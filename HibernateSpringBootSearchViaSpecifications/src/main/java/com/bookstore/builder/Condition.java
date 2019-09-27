package com.bookstore.builder;

public final class Condition {
    
    public enum LogicalPointerType {
        
        AND, OR, END
    }
    
    public enum OperationType {
        
        EQUAL, NOT_EQUAL, GREATER_THAN, LESS_THAN, LIKE
    }
    
    private final String leftHand;
    private final String rightHand;
    private final OperationType operation;
    private final LogicalPointerType pointer;

    public Condition(String leftHand, String rightHand, 
            OperationType operation, LogicalPointerType pointer) {
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.operation = operation;
        this.pointer = pointer;
    }

    public String getLeftHand() {
        return leftHand;
    }

    public String getRightHand() {
        return rightHand;
    }

    public OperationType getOperation() {
        return operation;
    }        

    public LogicalPointerType getPointer() {
        return pointer;
    }            
}
