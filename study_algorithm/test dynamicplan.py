def fib(n):
    m=map(0->1,1->1)
    if n not in keys(m):
        m[n]=fib(n-1)+fib(n-2)
    return m[n]
print(fib(4))


