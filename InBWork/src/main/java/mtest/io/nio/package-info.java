/**
 * 1. 阻塞I/O通信模型 阻塞I/O在调用InputStream.read()方法时是阻塞的，它会一直等到数据到来时（或超时）才会返回；
 * 同样，在调用ServerSocket.accept()方法时，也会一直阻塞到有客户端连接才会返回，每个客户端连接过来后，服务端都会启动一个线程去处理该客户端的请求。
 *
 * 2. java NIO原理及通信模型 Java NIO是在jdk1.4开始使用的，它既可以说成“新I/O”，也可以说成非阻塞式I/O。
 * 
 * NIO的工作原理： 由一个专门的线程来处理所有的 IO 事件，并负责分发。 事件驱动机制：事件到的时候触发，而不是同步的去监视事件。
 * 线程通讯：线程之间通过 wait,notify 等方式通讯。保证每次上下文切换都是有意义的。减少无谓的线程切换。
 * 
 * 在文件通道中read和write方法都是阻塞的，对于read方法，除非遇到文件结束，否则会把缓冲区的剩余空间读满再返回。
 * 对于write方法，会一次性把缓冲区中的内容全部写入到文件中才会返回。
 * 
 * 1.FileChannel：从文件中读写数据。
 * 
 * 2.DatagramChannel：能通过UDP读写网络中的数据。
 * 
 * 3.SocketChannel：能通过TCP读写网络中的数据。
 * 
 * 4.ServerSocketChannel：可以监听新近来的TCP协议，像Web服务器一样。对每个新进来的连接都会创建一个SocketChannel。
 */
package mtest.io.nio;