package transaction.self;

public interface Worker {
	
	void work(int value);
	
	void commit();
	
	void rollback();
}
