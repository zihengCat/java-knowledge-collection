# 计算机网络 - 传输层（Transport Layer)

网络层只把分组数据包发送到目的主机，但是真正通信的并不是主机而是主机中的进程。传输层提供了进程间的逻辑通信，传输层向高层用户屏蔽了下面网络层的核心细节，使应用程序看起来像是在两个传输层实体之间有一条端到端的逻辑通信信道。

# TCP 与 UDP 协议

- 传输控制协议 TCP （Transmission Control Protoco）：面向连接，提供可靠传输，带有拥塞控制，面向字节流（将应用层下传的数据报文看作字节流，按字节流组织大小不等的数据块），一对一（点对点）全双工通信。

- 用户数据报协议 UDP （User Datagram Protoco）：无连接，尽最大可能交付，无拥塞控制，面向数据报文（对于应用下传的数据报文不做拆分与合并，仅添加 UDP 报文头），支持一对一、一对多、多对一和多对多的交互通信。

# UDP 报文首部

![Network-TransportLayer-UDPHeader][Network-TransportLayer-UDPHeader]

> 图：UDP 报文头示意图

UDP 报文首部字段只有 8 个字节，包括源端口、目的端口、长度、检验和。12 字节的伪首部是为了计算检验临时添加的。

# TCP 报文首部

![Network-TransportLayer-TCPHeader][Network-TransportLayer-TCPHeader]

> 图：TCP 报文头示意图

- **序号** ：用于对字节流进行编号。例如：序号为 301，表示第一个字节的编号为 301，如果携带的数据长度为 100 字节，那么下一个报文段的序号应为 401。

- **确认号** ：期望收到的下一个报文段的序号。例如： B 正确收到 A 发送来的一个报文段，序号为 501，携带的数据长度为 200 字节，因此 B 期望下一个报文段的序号为 701，B 发送给 A 的确认报文段中确认号就为 701。

- **数据偏移** ：指的是数据部分距离报文段起始处的偏移量，实际上指的是报文首部的长度。

- **确认 ACK 标志位** ：当 ACK=1 时，确认号字段有效，否则无效。TCP 协议规定，在连接建立后所有传送的报文段都必须把 ACK 置 1。

- **同步 SYN 标志位** ：在连接建立时用来同步序号。当 SYN=1，ACK=0 时表示这是一个连接请求报文段。若对方同意建立连接，则响应报文中 SYN=1，ACK=1。

- **终止 FIN 标志位** ：用来释放一个连接，当 FIN=1 时，表示此报文段的发送方的数据已发送完毕，并要求释放连接。

- **滑动窗口** ：窗口值作为接收方让发送方设置其发送窗口的依据。之所以要有这个限制，是因为接收方的数据缓存空间是有限的。



[Network-TransportLayer-UDPHeader]: ../../images/Network-TransportLayer-UDPHeader.jpg

[Network-TransportLayer-TCPHeader]: ../../images/Network-TransportLayer-TCPHeader.jpg

<!-- EOF -->