T=int(input())
height=[]
weight=[]
num=[]
for i in range(T):
    H, W, N = map(int,input().split())
    height.append(H)
    weight.append(W)
    num.append(N)

for i in range(T):
    val=num[i]%height[i]
    if val==0:
        val=height[i]
    if num[i]==height[i]:
        val2=height[i]
    else:
        val2=int(num[i]/height[i])
        if num[i]/height[i]-val2!=0:
            val2=val2+1
    print(val*100+val2)
