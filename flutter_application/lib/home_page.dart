import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_application/date.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  // 좋아요 여부
  bool isFavorite = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Image.asset(
          'assets/logo1.png',
          height: 32,
        ),
        centerTitle: true,
        backgroundColor: Colors.white,
      ),
      body: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // 날짜

            Padding(
              padding: const EdgeInsets.all(5.0),
              child: Text(
                "최근 입력데이터 날짜  : 2023/06/01",
                style: TextStyle(
                  color: Colors.grey,
                ),
              ),
            ),

            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Center(
                  child: Text(
                "부산항(BUSAN HARRBOR)",
                style: TextStyle(
                  fontSize: 30,
                  color: const Color.fromARGB(255, 0, 0, 0),
                ),
              )),
            ),
            // 여기에 나중에 코드로 구현한 이미지 링크를 바꿔넣으면 될거같습니다.
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Image.network(
                "https://tse4.mm.bing.net/th?id=OIP.wO-mGBClApyoZhJq4qNZpwHaEA&pid=Api&P=0&h=220",
                height: 200,
                width: double.infinity,
                //fit: BoxFit.cover,
              ),
            ),

            // 아이콘 목록
            Row(
              children: [
                Spacer(), // 빈 공간 추가
                IconButton(
                  onPressed: () {
                    // 화면 갱신
                    setState(() {
                      isFavorite = !isFavorite; // 좋아요 토글
                    });
                  },
                  icon: Icon(
                    CupertinoIcons.bookmark,
                    color: isFavorite ? Colors.pink : Colors.black,
                  ),
                ),
              ],
            ),

            Padding(
              padding: const EdgeInsets.all(5.0),
              child: Text(
                ("선택한 날짜 : " + _selectedDate),
                style: TextStyle(
                  color: Colors.grey,
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Text(
                "딥러닝 예측결과",
                style: TextStyle(
                  fontWeight: FontWeight.bold,
                  fontSize: 20,
                ),
              ),
            ),
            Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                // 설명
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Text(
                    "@@미만의 확률로 강세장",
                    style: TextStyle(
                      fontSize: 15,
                      color: Color.fromARGB(255, 55, 0, 255),
                    ),
                  ),
                ),

                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Text(
                    "@@미만의 확률로 약세장",
                    style: TextStyle(
                      fontSize: 15,
                      color: Color.fromARGB(255, 255, 0, 0),
                    ),
                  ),
                ),

                Padding(
                  padding: const EdgeInsets.all(16.0),
                  child: Text(
                    "2023년 @월 @@일 부산항의 물동량은 @@@@로 예측됩니다",
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.trending_up),
            label: 'Trend',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: 'Home',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.account_circle),
            label: 'Account',
          ),
        ],
        currentIndex: 0, // 현재 선택된 메뉴
        selectedItemColor: Colors.amber,
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          print("클릭 되었습니다!");
          Navigator.push(
              context,
              MaterialPageRoute(
                  builder: (context) => const DatePickerScreen()));
        },
        child: const Icon(
          Icons.calendar_month,
          color: Colors.amber,
        ),
        backgroundColor: Colors.white,
      ),
    );
  }
}
