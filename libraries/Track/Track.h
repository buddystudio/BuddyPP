#ifndef Track_h
#define Track_h
class Track{
private:
	//定义引脚，下标0至4对应从左到右五个红外
	int Light[5];
	//定义阀值，用以区分黑和白
	int NUM[5];
	//若为true，则高于阈值时是白色；若为false，则高于阈值时是黑色。默认为true
	bool flag;

public:
	//用于存储读取的模拟量
	int value[5];
	Track(int a, int b, int c, int d, int e, bool f = true);
	void Mode();
	void Set(int a, int b, int c, int d, int e);
	void Test();
	int Digital_in(int a = 5);
};
#endif