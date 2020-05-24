import math
result=[]
for i in range(int(input())):
    x1,y1,r1,x2,y2,r2=map(int,input().split())
    mul=math.pow(x1-x2,2)+math.pow(y1-y2,2)
    if mul==0 and r1==r2:
            count=-1
    elif mul==math.pow(r1+r2,2) or mul==math.pow(r1-r2,2):
        count=1
    elif math.pow(r1-r2,2)<mul and mul<math.pow(r1+r2,2):
            count=2
    elif mul>math.pow(r1+r2,2) or mul<math.pow(r1-r2,2):
            count=0
    result.append(count)
for i in range(len(result)):
    print(result[i])
