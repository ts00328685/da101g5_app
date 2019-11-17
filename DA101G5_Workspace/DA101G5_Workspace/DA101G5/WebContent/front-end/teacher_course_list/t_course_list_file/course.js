var course = [];
//市
//course[0] = ['漢語','英語','西班牙語','俄語','法語','阿拉伯語','葡萄牙語','德語','義大利語','日語'];
course[0] = {'漢語':'L00001','英語':'L00002','西班牙語':'L00003','俄語':'L00004','法語':'L00005','阿拉伯語':'L00006','葡萄牙語':'L00007','德語':'L00008','義大利語':'L00009','日語':'L00010'};
//區
course["漢語"] = {'文法':'SC00001','口說':'SC00002','作文':'SC00003','聽力':'SC00004'};
course["英語"] = {'文法':'SC00001','口說':'SC00002','作文':'SC00003','聽力':'SC00004','雅思':'SC00005','遊學':'SC00006','商業':'SC00007','GRE':'SC00008','托福':'SC00009','多益':'SC00010'};
course["西班牙語"] = {'文法':'SC00001','口說':'SC00002','作文':'SC00003','聽力':'SC00004'};
course["俄語"] = {'文法':'SC00001','口說':'SC00002','作文':'SC00003','聽力':'SC00004'};
course["法語"] = {'文法':'SC00001','口說':'SC00002','作文':'SC00003','聽力':'SC00004'};
course["阿拉伯語"] = {'文法':'SC00001','口說':'SC00002','作文':'SC00003','聽力':'SC00004'};
course["葡萄牙語"] = {'文法':'SC00001','口說':'SC00002','作文':'SC00003','聽力':'SC00004'};
course["德語"] ={'文法':'SC00001','口說':'SC00002','作文':'SC00003','聽力':'SC00004'};
course["義大利語"] ={'文法':'SC00001','口說':'SC00002','作文':'SC00003','聽力':'SC00004'};
course["日語"] = {'文法':'SC00001','口說':'SC00002','作文':'SC00003','聽力':'SC00004'};


//初始化
function init_address(){
    	
    var language = document.getElementById('language');
    var sort_course = document.getElementById('sort_course');
    
    language.options.add(first());
	  sort_course.options.add(first());
    
    //市
	for(var i in course[0]){
        o=document.createElement('option');
        o.text=course[0][i];
        o.value=course[0][i];
        language.options.add(o);
	}
    
    //區
	language.onchange=function(){
	   
        document.getElementById('courseCode').value = "";
       
		sort_course.innerHTML='';
		sort_course.options.add(first());
        
		if(this.selectedIndex>0){
    		for(var i in course[this.value]){                
    			o=document.createElement('option');
                o.text = i;
    			o.value = i;
    			sort_course.options.add(o);
    		}
        }
	};
    
    //郵遞區號
    sort_course.onchange=function(){
		if(this.selectedIndex>0)
            document.getElementById('courseCode').value = course[language.value][sort_course.value];
	};
}

//第一個選項
function first(){
	var o=document.createElement('option');
	o.text='請選擇';
	o.value="";
	return o;
}