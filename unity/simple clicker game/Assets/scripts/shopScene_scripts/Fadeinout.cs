using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class Fadeinout : MonoBehaviour
{
    
    
    [HideInInspector]
    public float textfades = 0.1f; // 0~1 값을 넣을 수 있다
    public float imagefades = 0.13f; 
    public Image fadeimage;
    public Text fadetext;
    public float time = 0;
    public bool fadeinout = false;
    // Start is called before the first frame update
    void Start()
    {
        fadeimage = GameObject.Find("fade").GetComponent<Image>();
        fadetext = GameObject.Find("fadetext").GetComponent<Text>();
        StartCoroutine(fadein("어서오세요"));
    }

    // Update is called once per frame
    
    
    public IEnumerator fadein(string text)
    {
        fadetext.text = text;
        for (float f = 1f; f >= 0; f -= 0.01f)
        {
            Color c = fadetext.color;
            Color c1 = fadeimage.color;
            c.a = f;
            c1.a = f;
            fadetext.color = c;
            fadeimage.color = c1;
            yield return null;
        }
    }
}
