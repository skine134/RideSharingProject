N, K=map(int,input().split(' '))
obj=[0 for col in range(2) for row in range(N)]
backpack=[0 for col in range(N)]
for i in range(N):
    obj[i][0],obj[i][1] = map(int,input().split(' '))

#find best answer
for i in range(N):
    arr=[]
    if i==0:
        for j in range(N):
            arr2=[obj[j]]
            if(arr2[0][1]<=K)
                arr.append(arr2)
    else:
        for j in range(N):
            arr2=[]
            for k in range(j+1,N-i):
                arr2.append(obj[j]+backpack[i][k])
                
            if(sum(arr2[1])<=K)
                arr.append(arr2)
    backpack[i]=arr
print(arr)
        
