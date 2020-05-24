def incept(arr1, arr2, num,M):
    #arr3=arr1.copy()
    if M>=len(arr2):
        for i in range(0,len(arr1)):
            #arr4=arr3.copy()
            arr2.insert(num,arr1.pop(i))
            incept(arr1,arr2,num+1,M)
    if len(arr2)==M:
        for i in range(0,len(arr2)):
            print(arr2[i],end=' ')
        print()
    arr1.pop(0)
    if len(arr2)!=0:
        for i in range(num-1,len(arr2)):
            arr2.pop(i)
N, M=input().split(' ')
a=[]
b=[]
for i in range(1,int(N)+1):
    a.insert(i-1,i)
incept(a,b,0,int(M))
