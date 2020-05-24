def fibonacci(n):
    if n==0:
        return 1,0
    elif n==1:
        return 0,1
    elif n==2:
        return 1,1
    else:
        zero, one=fibonacci(n-1)
        nextzero=one
        one=one+zero
        zero=nextzero
        return zero, one
T=int(input())
arr=[]
for i in range(T):
    arr.append(int(input()))
for i in range(len(arr)):
    arr2=fibonacci(arr[i])
    print(arr2[0],arr2[1])
