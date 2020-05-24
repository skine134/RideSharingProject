n=int(input())
arr=[]
max_num=1
for i in range(n):
    count=1
    weight,height=list(map(int, input().split()))
    if len(arr)==0:
        num=1
    else:
        for j in range(len(arr)):
            if arr[j]['weight']>weight and arr[j]['height']>height:
                count=count+1
            elif arr[j]['weight']<weight and arr[j]['height']<height:
                arr[j]['num']=arr[j]['num']+1
        num=count
    dict={'weight':weight,'height':height,'num':num}
    arr.append(dict)
    
for i in range(len(arr)):
    print(arr[i].get('num'), end=' ')
