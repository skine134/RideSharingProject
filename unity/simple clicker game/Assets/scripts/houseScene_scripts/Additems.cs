using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Additems : MonoBehaviour
{
    public GameObject go;
    private int count = 0;
    // Start is called before the first frame update
    public void Awake()
    {
        for (int i = 0; i < state.items.Count; i++)
        {
            GameObject item = Instantiate(go, transform.position + new Vector3(0, count * 50),Quaternion.identity, transform) as GameObject;
            item.SetActive(true);
            GameObject item_image=item.transform.GetChild(0).gameObject;
            GameObject item_tag = item.transform.GetChild(1).gameObject;
            GameObject item_name = item.transform.GetChild(2).gameObject;
            GameObject item_count = item.transform.GetChild(3).gameObject;
            item_image.GetComponent <Image>().sprite= state.items[i].getImage();
            item_tag.GetComponent<Text>().text = state.items[i].getTag().ToString();
            item_name.GetComponent<Text>().text = state.items[i].getName().ToString();
            item_count.GetComponent<Text>().text = state.items[i].getCount().ToString();
            count -= 1;
        }
    }
}
