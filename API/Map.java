package sql;

import java.util.*;
public class Map<K,V> extends HashMap<K,V> {

	/**
	 * @param args
	 */
	public void removeByValue(Object value) 
	{
		for (Object key : keySet())
		{
			if (get(key) == value)
			{
				remove(key);
				break;
			}
		}
	}
	public Set<V> valueSet() 
	{
		Set<V> result = new HashSet<V>();
		for (K key : keySet()){
			result.add(get(key));
		}
		return result;
	}
	public K getKeyByValue(V val) 
	{

		for (K key : keySet()){
			if (get(key).equals(val) 
				&& get(key) == val)
			{
				return key;
			}
		}
		return null;
	}
	public V put(K key,V value){
		for (V val : valueSet() ){
			if (val.equals(value)
				&& val.hashCode()== value.hashCode())
			{
				throw new RuntimeException(
					"MyMap can't have more than 2 same values!"); 
			}
		}
		return super.put(key , value);
	}

}
