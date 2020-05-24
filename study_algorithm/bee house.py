n=int(input())
i=1
while (n!=1) :
    i=i+1
    if((i-1)*(i-2)*3+1<n and n<=(i)*(i-1)*3+1):
        break
print(i)
