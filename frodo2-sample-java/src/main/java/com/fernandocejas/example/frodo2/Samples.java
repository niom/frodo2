package com.fernandocejas.example.frodo2;

import com.fernandocejas.example.frodo2.ObservableSamples.MyDummyClass;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.TimeUnit;

class Samples {

  private final ObservableSamples observableSamples = new ObservableSamples();

  private final CompositeDisposable disposables = new CompositeDisposable();

  Samples() {
    //empty
  }

  private void addDisposable(Disposable disposable) {
    disposables.add(disposable);
  }

  //------------------------------------------------
  // F L O W A B L E      S A M P L E S
  //------------------------------------------------
  void runFlowableExamples() {

  }

  //------------------------------------------------
  // O B S E R V A B L E      S A M P L E S
  //------------------------------------------------
  void runObservableExamples() {
    executeRxObservableSampleOne();
    executeRxObservableSampleTwo();
    executeRxObservableSampleThree();
  }

  private void executeRxObservableSampleOne() {
    final Observable<Integer> integers =
        observableSamples.numbers().subscribeOn(Schedulers.newThread());
    addDisposable(integers.subscribeWith(new MyObserver<>()));

    final Observable<String> strings = observableSamples.strings()
        .delay(2, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.newThread());
    disposables.add(strings.subscribe());

    final Observable<String> error = observableSamples.error().delay(4, TimeUnit.SECONDS);
    disposables.add(error.subscribeWith(new MyObserver<>()));
  }

  private void executeRxObservableSampleTwo() {
    final Observable<Void> voidObservable =
        observableSamples.doNothing().delay(8, TimeUnit.SECONDS).subscribeOn(Schedulers.io());
    disposables.add(voidObservable.subscribeWith(new MyObserver<>()));

    final Observable<MyDummyClass> dummyClassObservable = observableSamples.doSomething()
        .delay(10, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io());
    disposables.add(dummyClassObservable.subscribe());
  }

  private void executeRxObservableSampleThree() {
    final Observable<String> stringWithDefer = observableSamples.stringItemWithDefer()
        .subscribeOn(Schedulers.newThread())
        .observeOn(Schedulers.single());
    disposables.add(stringWithDefer.subscribeWith(new MyObserver<>()));

    final Observable<String> stringObservable = observableSamples.manualCreation();
    disposables.add(stringObservable.subscribe());

    final Observable<List<MyDummyClass>> listObservable = observableSamples.list()
        .subscribeOn(Schedulers.newThread())
        .observeOn(Schedulers.single());
    disposables.add(listObservable.subscribe());
  }

  //------------------------------------------------
  // S I N G L E      S A M P L E S
  //------------------------------------------------
  void runSingleExamples() {

  }

  //------------------------------------------------
  // M A Y B E      S A M P L E S
  //------------------------------------------------
  void runMaybeExamples() {

  }

  //------------------------------------------------
  // C O M P L E T A B L E      S A M P L E S
  //------------------------------------------------
  void runCompletableExamples() {

  }
}
