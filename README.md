# android studio

==simple adapter==


```
SimpleAdapter adapter 
= new SimpleAdapter(getApplicationContext(), 
                    fillMaps, 
                    R.layout.rss, 
                    from, 
                    to);
```
### parameter intro.
1. context(context)
看起來應該就是抓到目前這app的main xml, 也可以改成this就好, 
但是你要是寫在try/catch裡面, 就要寫getapp...(), 因為裡面的this不是指context

2. fillmaps(hashmap)
應該就是這個adapter class要求的format, 配合from/to, from會得到
一個index, 之後去查hashmap中的index對到的所有data, 丟到to, 
而這個參數就是那個hashmap

3. element in list(xml)
你的element要長怎樣都在這個xml裡面

4. from(index)
是放index in hashmap,  不是data in hashmap
之後會把index底下的所有data都塞進去list裡面
5. to(component in xml)
對應到from的順序, 一個對一個, 這邊就是index的東西都使用R.id.for_text這個component

