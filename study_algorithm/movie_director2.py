n=int(input())
count=0
num=666
while True:
    arr=list(str(num))
    for i in range(len(arr)-2):
        if  arr[i]=='6'and arr[i+1]=='6' and arr[i+2]=='6':
            count=count+1
            break
    if count==n:
        break        
    num=num+1
print(num)
