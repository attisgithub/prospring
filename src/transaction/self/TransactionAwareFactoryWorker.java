package transaction.self;

import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class TransactionAwareFactoryWorker implements WorkerFactory {

	@Override
	public Worker create() {
		if(TransactionSynchronizationManager.hasResource(this)){
			return getTransactionBoundWorker();
		}else{
			return createNewTransactionBoundWorker();
		}

	}

	private Worker createNewTransactionBoundWorker() {
		Worker worker = new DefaultWorker();
		WorkerFactoryContext context = new WorkerFactoryContext(worker);
		TransactionSynchronization synchronization = new WorkerTransactionSynchronization(this);
		TransactionSynchronizationManager.registerSynchronization(synchronization);
		TransactionSynchronizationManager.bindResource(this, context);
		return worker;
	}

	private Worker getTransactionBoundWorker() {
		WorkerFactoryContext context = (WorkerFactoryContext) TransactionSynchronizationManager.getResource(this);
		return context.getWorker();
	}

}
