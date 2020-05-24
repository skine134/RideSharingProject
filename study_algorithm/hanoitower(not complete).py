def hanoi(first,midle,goal,num):
    if first[len(first)-1]!=num:
        hanoi(fisrt,midle,goal,num-1)
    else:
        print()



size=int(input())
arr=[[],[],[]]
for i in range(size):
    arr[0].append(i+1)
hanoi(arr[0],arr[1],arr[2],size)
