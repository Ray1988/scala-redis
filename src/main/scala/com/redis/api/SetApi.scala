package com.redis.api

import com.redis.serialization.{Format, Parse}

trait SetApi {

  /**
   * Add the specified members to the set value stored at key. (VARIADIC: >= 2.4)
   */
  def sadd(key: Any, value: Any, values: Any*)(implicit format: Format): Option[Long]

  /**
   * Remove the specified members from the set value stored at key. (VARIADIC: >= 2.4)
   */
  def srem(key: Any, value: Any, values: Any*)(implicit format: Format): Option[Long]

  /**
   * Remove and return (pop) a random element from the Set value at key.
   */
  def spop[A](key: Any)(implicit format: Format, parse: Parse[A]): Option[A]

  /**
   * Remove and return multiple random elements (pop) from the Set value at key since (3.2).
   */
  def spop[A](key: Any, count: Int)(implicit format: Format, parse: Parse[A]): Option[Set[Option[A]]]

  /**
   * Move the specified member from one Set to another atomically.
   */
  def smove(sourceKey: Any, destKey: Any, value: Any)(implicit format: Format): Option[Long]

  /**
   * Return the number of elements (the cardinality) of the Set at key.
   */
  def scard(key: Any)(implicit format: Format): Option[Long]

  /**
   * Test if the specified value is a member of the Set at key.
   */
  def sismember(key: Any, value: Any)(implicit format: Format): Boolean

  /**
   * Return the intersection between the Sets stored at key1, key2, ..., keyN.
   */
  def sinter[A](key: Any, keys: Any*)(implicit format: Format, parse: Parse[A]): Option[Set[Option[A]]]

  /**
   * Compute the intersection between the Sets stored at key1, key2, ..., keyN,
   * and store the resulting Set at dstkey.
   * SINTERSTORE returns the size of the intersection, unlike what the documentation says
   * refer http://code.google.com/p/redis/issues/detail?id=121
   */
  def sinterstore(key: Any, keys: Any*)(implicit format: Format): Option[Long]

  /**
   * Return the union between the Sets stored at key1, key2, ..., keyN.
   */
  def sunion[A](key: Any, keys: Any*)(implicit format: Format, parse: Parse[A]): Option[Set[Option[A]]]

  /**
   * Compute the union between the Sets stored at key1, key2, ..., keyN,
   * and store the resulting Set at dstkey.
   * SUNIONSTORE returns the size of the union, unlike what the documentation says
   * refer http://code.google.com/p/redis/issues/detail?id=121
   */
  def sunionstore(key: Any, keys: Any*)(implicit format: Format): Option[Long]

  /**
   * Return the difference between the Set stored at key1 and all the Sets key2, ..., keyN.
   */
  def sdiff[A](key: Any, keys: Any*)(implicit format: Format, parse: Parse[A]): Option[Set[Option[A]]]

  /**
   * Compute the difference between the Set key1 and all the Sets key2, ..., keyN,
   * and store the resulting Set at dstkey.
   */
  def sdiffstore(key: Any, keys: Any*)(implicit format: Format): Option[Long]

  /**
   * Return all the members of the Set value at key.
   */
  def smembers[A](key: Any)(implicit format: Format, parse: Parse[A]): Option[Set[Option[A]]]

  /**
   * Return a random element from a Set
   */
  def srandmember[A](key: Any)(implicit format: Format, parse: Parse[A]): Option[A]

  /**
   * Return multiple random elements from a Set (since 2.6)
   */
  def srandmember[A](key: Any, count: Int)(implicit format: Format, parse: Parse[A]): Option[List[Option[A]]]

  /**
   * Incrementally iterate Set elements (since 2.8)
   */
  def sscan[A](key: Any, cursor: Int, pattern: Any = "*", count: Int = 10)(implicit format: Format, parse: Parse[A]): Option[(Option[Int], Option[List[Option[A]]])]

}
