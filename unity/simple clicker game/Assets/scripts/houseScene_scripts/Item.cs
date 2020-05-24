using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Item : MonoBehaviour
{
    public enum Tag { equitment, material, potion };
    private Tag tag;
    private string name;
    private string s;
    private int count;
    private Sprite image;
    public Item() { }
    public Item(Tag tag,string name,int count,string location)
    {
        this.tag = tag;
        this.name = name;
        this.count = count;
        this.image = Resources.Load < Sprite > (location);
    }
    public string getName()
    {
        return name;   
    }
    public void setName(string name)
    {
        this.name = name;
    }

    public string getS()
    {
        return s;
    }
    public void setS(string s)
    {
        this.s = s;
    }
    public void setTag(Tag tag)
    {
        this.tag = tag;
    }
    public Tag getTag()
    {
        return tag;
    }
    public int getCount()
    {
        return count;
    }
    public void setCount(int count)
    {
        this.count = count;
    }

    public int addCount()
    {
        count += 1;
        return count;
    }
    public int subCount()
    {
        if( count >= 1)
        {
            count -= 1;
        }
        return count;
    }
    public void setImage(string location)
    {
        this.image= Resources.Load<Sprite>(location);
    }
    public Sprite getImage()
    {
        return this.image;
    }
}
