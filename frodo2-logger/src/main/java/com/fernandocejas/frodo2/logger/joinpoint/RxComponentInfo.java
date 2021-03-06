package com.fernandocejas.frodo2.logger.joinpoint;

public class RxComponentInfo {
  private final RxComponent rxComponent;
  private final FrodoJoinPoint joinPoint;

  private String subscribeOnThread;
  private String observeOnThread;
  private long totalExecutionTime;
  private int totalEmittedItems;

  public RxComponentInfo(RxComponent rxComponent, FrodoJoinPoint joinPoint) {
    this.rxComponent = rxComponent;
    this.joinPoint = joinPoint;
  }

  public String rxComponentName() {
    return rxComponent.toString();
  }

  public String classSimpleName() {
    return joinPoint.getClassSimpleName();
  }

  public String methodName() {
    return joinPoint.getMethodName();
  }

  public FrodoJoinPoint joinPoint() {
    return joinPoint;
  }

  public String subscribeOnThread() {
    return subscribeOnThread;
  }

  public String observeOnThread() {
    return observeOnThread;
  }

  public long totalExecutionTime() {
    return totalExecutionTime;
  }

  public int totalEmittedItems() {
    return totalEmittedItems;
  }

  public void setSubscribeOnThread(String subscribeOnThread) {
    this.subscribeOnThread = subscribeOnThread;
  }

  public void setObserveOnThread(String observeOnThread) {
    this.observeOnThread = observeOnThread;
  }

  public void setTotalExecutionTime(long totalExecutionTime) {
    this.totalExecutionTime = totalExecutionTime;
  }

  public void setTotalEmittedItems(int totalEmittedItems) {
    this.totalEmittedItems = totalEmittedItems;
  }
}
