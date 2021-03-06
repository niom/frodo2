package com.fernandocejas.frodo2.logger.observable;

import com.fernandocejas.frodo2.logger.joinpoint.RxComponentInfo;
import com.fernandocejas.frodo2.logger.logging.MessageManager;
import com.fernandocejas.frodo2.test.UnitTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.observers.TestObserver;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@SuppressWarnings("unchecked")
public class FrodoForObservableTest extends UnitTest {

  @Rule public ObservableRule observableRule = new ObservableRule(this.getClass());

  private FrodoForObservable frodoObservable;
  private TestObserver observer;

  @Mock private MessageManager messageManager;

  @Before
  public void setUp() {
    frodoObservable = new FrodoForObservable(observableRule.joinPoint(), messageManager);
    observer = new TestObserver();
  }

  @Test
  public void shouldBuild() throws Throwable {
    frodoObservable.observable().subscribe(observer);

    observer.assertResult(ObservableRule.OBSERVABLE_STREAM_VALUE);
    observer.assertNoErrors();
    observer.assertComplete();
  }

  @Test
  public void shouldPrintInfo() throws Throwable {
    frodoObservable.observable();

    verify(messageManager).printRxComponentInfo(any(RxComponentInfo.class));
  }

  @Test
  public void shouldLogInformation() throws Throwable {
    frodoObservable.observable().subscribe(observer);
    observer.dispose();

    verify(messageManager).printRxComponentInfo(any(RxComponentInfo.class));
    verify(messageManager).printOnSubscribe(any(RxComponentInfo.class));
    verify(messageManager).printOnNextWithValue(any(RxComponentInfo.class), any());
    verify(messageManager).printOnComplete(any(RxComponentInfo.class));
    verify(messageManager).printOnTerminate(any(RxComponentInfo.class));
    verify(messageManager).printItemTimeInfo(any(RxComponentInfo.class));
    verify(messageManager).printThreadInfo(any(RxComponentInfo.class));
    verify(messageManager).printOnDispose(any(RxComponentInfo.class));
  }
}
