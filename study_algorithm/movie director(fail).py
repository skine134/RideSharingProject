n=int(input())
arr=[]
count=0
_sum=0
j=0
before_sum=0
before_count=0
result=0
def findline(number):
    count=0
    _sum=0
    j=3
    before_sum=0
    while True:
        count=count*8+math.pow(10,j-3)
        before_sum=_sum
        _sum=_sum+count
        if number<=_sum:
            return _sum, before_sum, count,before_count, j, number
        before_count=count
        j=j+1
def findnumber(_sum, before_sum, count,before_count,line, number):
    n=number-before_sum+1
    if n//before_count<6 and n//before_count>6:
        
    if n//before_count==6:
        666*math.pow(10,line-3)+n%before_count
