package com.tamguo.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamguo.util.ObjectUtil;
import com.tamguo.util.SerializeTranscoder;

import redis.clients.jedis.ShardedJedis;

/**
 * 缓存中心
 * 
 */
@Service("cacheService")
public class CacheService {
	private final static String REDIS_PRE_KEY = "TAMGUO:";
	private SerializeTranscoder objectSerialize = new ObjectUtil();

	@Autowired
	private RedisXMLConfigure redisXMLConfigure;

	/**
	 * 
	 * @Title: get @Description: @param @return String 返回类型 @throws
	 */
	public String get(String key) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			return conn.get(key);
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}

	/**
	 * 
	 * @Title: set @Description: @param @return void 返回类型 @throws
	 */
	public void set(String key, String value) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			conn.set(key, value);
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}

	/**
	 * 
	 * set 设置带过期时间的字符缓存
	 * 
	 * @param key
	 * @param value
	 * @param time
	 *            过期时间，秒
	 * @description
	 * @exception @since
	 *                1.0.0
	 */
	public void set(String key, String value, int time) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			conn.set(key, value);
			conn.expire(key, time);
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}

	/**
	 * redis中存放对象
	 * 
	 * @param key 对象key
	 * @param value 可序列化的对象
	 */
	public void setObject(String key, Object value) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			conn.set(key.getBytes(), objectSerialize.serialize(value));
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}

	/**
	 * 设置过期时间存储对象
	 * 
	 * @param key 对象key
	 * @param value 对象值
	 * @param time 过期时间 秒
	 */
	public void setObject(String key, Object value, int time) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			conn.setex(key.getBytes(), time, objectSerialize.serialize(value));
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}

	/**
	 * 获取存储的对象
	 * 
	 * @param key 对象key
	 * @return 存储的对象
	 */
	public Object getObject(String key) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			byte[] obj = conn.get(key.getBytes());
			if (null == obj)
				return null;
			return objectSerialize.deserialize(obj);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
		return null;
	}

	/**
	 * 删除一个对象
	 * 
	 * @param key  对象key值
	 * @return
	 */
	public boolean deleteObject(String key) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			return conn.del(key.getBytes()) == 1L;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
		return false;
	}

	/**
	 * 
	 * @Title: isExist @Description: 判断key是否存在 @param @return boolean
	 * 返回类型 @throws
	 */
	public boolean isExist(String key) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			return conn.exists(key);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
		return false;
	}

	public boolean notExist(String key) {
		return !isExist(key);
	}

	public boolean delete(String key) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			return conn.del(key) == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
		return false;
	}

	/**
	 * 关于 redis list的操作 将 值 value 插入到列表 key 的表尾(最右边)。
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public long putToListEnd(String key, String value) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			long length = conn.rpush(key, value);
			return length;
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}

	/**
	 * 将value插入集合key的尾部, 并设置过期时间
	 * 
	 * @author zhangxin
	 * @param key
	 * @param value
	 * @param seconds
	 * @param score
	 * @return long 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	public long addToSortedSetAndExpire(String key, String value, int seconds, double score) {
		return addToSortedSet(key, value, seconds, true, score);
	}
	
	
	/**
	 * 将value插入集合key的尾部  增加value的score
	 * 
	 * @author zhangxin
	 * @param key
	 * @param value
	 * @param score
	 * @return long 被成功添加的新成员的分数
	 */
	public double addToSortedSetScore(String key, String value, double score) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			Double zincrby = conn.zincrby(key, score, value);
			return zincrby;
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}
	
	/**
	 * 获取member的Score
	 * @param key
	 * @param value
	 * @return
	 */
	public Double getMemberScore(String key, String member) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			Double zscore = conn.zscore(key, member);
			return zscore == null ? 0 : zscore;
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}
	

	/**
	 * 将value插入集合key的尾部, 不设置过期时间
	 * 
	 * @author zhangxin
	 * @param key
	 * @param value
	 * @param score
	 * @return long 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	public long addToSortedSet(String key, String value, double score) {
		return addToSortedSet(key, value, -1, false, score);
	}
	
	
	/**
	 * 判断member在集合里是否存在
	 * 
	 * @return isExist 存在 true 不存在 
	 */
	public boolean isExistSortedSet(String key, String member) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			Long zrank = conn.zrank(key, member);
			return zrank != null;
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}
	
	/**
	 * 删除member
	 * 
	 * @return isExist 存在 true 不存在 
	 */
	public boolean delSortedSetMember(String key, String[] member) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			Long zrem = conn.zrem(key, member);
			return zrem >= 1;
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}

	/**
	 * 将value插入集合key的尾部, 对于setExpire为false的情况, seconds无效
	 * 
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	private long addToSortedSet(String key, String value, int seconds, boolean setExpire, double score) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			long addNum = conn.zadd(key, score, value);
			if (setExpire) {
				conn.expire(key, seconds);
			}
			return addNum;
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}

	/**
	 * 按score降序分页获取有序集合中内容
	 * 
	 * @author zhangxin
	 * @param key
	 * @param pageNo
	 *            首页从1开始
	 * @param pageSize
	 * @return Set<String>
	 */
	public Set<String> getSortedSetByPage(String key, int pageNo, int pageSize) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			if (pageNo < 1) {
				pageNo = 1;
			}
			if (pageSize < 1) {
				pageSize = 1;
			}
			int start = (pageNo - 1) * pageSize;
			conn = redisXMLConfigure.getConnection();
			return conn.zrevrange(key, start, start + pageSize - 1);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
		return null;
	}

	public List<String> getListHead(String key) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			List<String> result = conn.blpop(1000, key);

			if (null == result || result.size() == 0)
				return null;
			return result;
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}

	/**
	 * 存储map
	 * 
	 * @param key 键值
	 * @param field map field
	 * @param value map value
	 * @return if filed exist return 0 else return 1
	 */
	public Long hset(String key, String field, String value) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			return conn.hset(key, field, value);
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}

	public String hset(String key, Map<String, String> values) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			return conn.hmset(key, values);
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}

	public String hset(String key, Map<String, String> values, int time) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			String hmset = conn.hmset(key, values);
			conn.expire(key, time);
			return hmset;
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}

	/**
	 * 得到map中存储的field值
	 * 
	 * @param key 键值
	 * @param field map field
	 * @return
	 */
	public String hget(String key, String field) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			return conn.hget(key, field);
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}

	/**
	 * 名称为key的string减1操作
	 * 
	 * @param key
	 * @return
	 */
	public Long decr(String key) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			return conn.decr(key);
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}
	
	/**
	 * 名称为key的string加1操作
	 * 
	 * @param key
	 * @return
	 */
	public Long incr(String key) {
		key = getPreKey(key);
		ShardedJedis conn = null;
		try {
			conn = redisXMLConfigure.getConnection();
			return conn.incr(key);
		} finally {
			redisXMLConfigure.closeConnection(conn);
		}
	}

	private String getPreKey(String key) {
		String temp_pre = redisXMLConfigure.getPreKey();
		if (null == temp_pre) {
			return REDIS_PRE_KEY + key;
		}
		return temp_pre + key;
	}

}
