/**
 * CountDownLatch是java.util.concurrent包下面的一个工具类，
 * 可以用来协调多个线程之间的同步，或者说起到线程之间的通信（而不是用作互斥的作用）。 它可以允许一个或者多个线程等待其他线程完成操作。
 * 
 * 模拟游戏一开始需要加载一些基础数据后才能开始游戏， 基础数据加载完可以继续加载其他数据。基础数据包含人物、地图、背景、物品等等。
 * 
 * copy from：https://juejin.im/post/5b873d756fb9a019e9768851
 */
package mtest.multithread.CountDownLatch;