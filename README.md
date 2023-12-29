به نام خدا

# آزمایش هشتم آزمایشگاه مهندسی نرم‌افزار (Profiling)
## لینک آزمایش
https://github.com/kianomoomi/SELab8/

## مقدمات آزمایش
در این آزمایش قصد داریم تا با پروفایل کردن برنامه‌ها آشنا شویم.
## شرح آزمایش
### بخش ۱
در این بخش، ابتدا فایل javacup را بدون بهینه‌سازی اجرا کرده و در yourkit وضعیت مصرف منابع آن‌ و بخش‌هایی از قطعه کد که بیشترین مصرف را ایجاد می‌کنند، بررسی میکنیم.

اسکرین‌شات‌های زیر به ترتیب این مراحل را نشان میدهند:

- خروجی اجرای کد به ازای ورودی 1,2,3 ("No")
  ![Screenshot 2023-12-29 234333](https://github.com/kianomoomi/SELab8/assets/59165380/1c416513-221f-4807-b1e6-4db73b3b3f4d)
![Screenshot 2023-12-29 234423](https://github.com/kianomoomi/SELab8/assets/59165380/e4cf242a-67a3-4174-b509-36a80882b3f3)
![Screenshot 2023-12-29 234508](https://github.com/kianomoomi/SELab8/assets/59165380/f44a8777-0d08-454d-9d50-9b94c0c052da)

همانطور که مشاهده می‌کنیم، تابع temp اکثر ریسورس را مصرف می‌کند.
حال کد فانکشن temp را که یک حلقه for تو در تو است را بهینه میکنیم به طوری که به جای استفاده از ArrayList از آرایه built-in جاوا استفاده کند. با اینکار مصرف منابع بسیار کاهش می‌یابد و میزان runtime در تابع temp به چند میلی ثانیه کاهش میابد. کد حاصل را در فایل JavaCup2.java ذخیره می‌کنیم. کد temp تغییر یافته به صورت زیر است:
<pre style="direction: ltr;">
<code style="direction: ltr;">
public static void temp() {
  int[] a = new int[20000 * 10000];
  int index = 0;
   for (int i = 0; i < 10000; i++) {
       for (int j = 0; j < 20000; j++) {
           a[index++] = i + j;
        }
    }
}
</code>
</pre>
حال دوباره برنامه را اجرا می کنیم.
![Screenshot 2023-12-29 235319](https://github.com/kianomoomi/SELab8/assets/59165380/b1cdbe13-9ab5-4ee9-8b3a-ec62b7f70dc1)
![Screenshot 2023-12-29 235421](https://github.com/kianomoomi/SELab8/assets/59165380/518b27f3-9a0a-4dfd-8b10-fbd3e25e8032)
![Screenshot 2023-12-29 235627](https://github.com/kianomoomi/SELab8/assets/59165380/f84df35d-adca-4c57-b032-37250d868d64)
مشاهده می‌شود که مصرف منابع به طور قابل توجهی کاهش یافته است.

### بخش ۲

در این قسمت، کد پیاده‌سازی شده به ازای یک ماتریس باید به تعداد دلخواه ضرب این ماتریس در خودش را حساب کند. در این آزمایش ما به ازای ماتریس

<table>
  <tr>
    <td>2</td>
    <td>4</td>
  </tr>
  <tr>
    <td>6</td>
    <td>8</td>
  </tr>
</table>
حاصل ضرب آن در خودش به تعداد ۲ به توان ۳۰ بار را محاسبه میکنیم.
در حالت غیر بهینه این ماتریس را به همین تعداد در ماتریسی که در بالا مشخص شده است ضرب میکنیم. این کد را در Sample.java ذخیره میکنیم.

اسکرین‌شات‌های زیر مراحل را به ترتیب نشان می‌دهند:
![Screenshot 2023-12-30 002312](https://github.com/kianomoomi/SELab8/assets/59165380/e8a57adc-f7d8-46c8-9749-927016b2ac4b)
![Screenshot 2023-12-30 002421](https://github.com/kianomoomi/SELab8/assets/59165380/2e3d6383-c1dc-4740-b730-bc9d5b9364c3)
![Screenshot 2023-12-30 002502](https://github.com/kianomoomi/SELab8/assets/59165380/c10cd7bb-d788-4a12-af19-e170952a8865)
همانطور که مشخص است، فانکشن powerMatrix بیشترین مصرف و بیشترین runtime را دارد.
کد قبل را به نحوی بهینه میکنیم که به جای ۲ به توان ۳۰ بار، فقط کافی است ۳۰ بار ماتریس را در خودش ضرب کنیم. یعنی به جای اینکه هر بار result فعلی را در ماتریس اولیه ضرب کنیم،‌ result را در خودش ضرب می‌کنیم. کد حاصل را در Sample2.java ذخیره میکنیم. طبق نمودار‌ها مصرف منابع و runtime بسیار کاهش یافته است. لازم به ذکر است به منظور مشاهده نمودارها از آنجایی که این برنامه لحظه‌ای پایان می‌یابد، توان یعنی عدد ۳۰ را از ورودی می‌گیریم که صرفا نمایی از مصرف cpu و memory را داشته باشیم. کد تغییر یافته powerMatrix به صورت زیراست:

<pre style="direction: ltr;">
<code style="direction: ltr;">
public static long[][] powerMatrix(long[][] matrix, int power) {
  long[][] result = matrix;
      for (int i = 0; i < power; i++) {
          result = multiplyMatrix(result, result);
      }
    return result;
}
</code>
</pre>
حال دوباره برنامه را اجرا می‌کنیم و خواهیم داشت:
![Screenshot 2023-12-30 004302](https://github.com/kianomoomi/SELab8/assets/59165380/0da7a169-1ccf-442d-bfc5-0cba2c99b0f1)
![Screenshot 2023-12-30 004450](https://github.com/kianomoomi/SELab8/assets/59165380/a08b93e7-0986-4308-8394-e476aa87948d)
![Screenshot 2023-12-30 004530](https://github.com/kianomoomi/SELab8/assets/59165380/0c463e3d-e261-4221-8fc4-be63e3addb35)
همانطور که مشخص است متد powerMatrix بدلیل runtime بسیار پایین در بین متد‌ها نیست.
