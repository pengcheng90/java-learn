# LockSupport
LockSupport.park()可以让线程阻塞(停止，object的wait，await实现类似效果)，unpark()解除停止，
unpark（）可以先于park（）调用，先unpark()后park()不会停止,更灵活些相对wait、await。


