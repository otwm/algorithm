/**
 * N queen 문제 풀이
 * <p/>
 * Created by kim-dong-o on 16. 3. 5.
 */
public class Main {
    /**
     * 완료체크 변수
     */
    private static int done;
    /**
     * 카운트
     */
    private static int count;
    /**
     * N 절반 위치
     */
    private static int half;
    /**
     * 짝수 체크
     */
    private static boolean isPair;

    /**
     * 실행
     *
     * @param N 주어진 N
     * @return 정답
     */
    private static int execute(int N) {
        //초기화
        count = 0;
        //절반 값 할당
        half = (int) Math.pow(2, N / 2);
        //짝수 체크
        isPair = N % 2 == 0;
        //완료 체크 값 할당
        done = (int) Math.pow(2, N) - 1;
        //체크 수행. 처음 이기에 비어있는 체스판 0,0,0 으로 시작된다.논리적으로는 0000,0000,0000으로 생각하면 된다.
        check(0, 0, 0, true);
        //카운트 반환
        return count;
    }

    /**
     * 값 체크
     *
     * @param topToRight 우측 대각
     * @param forward    정면방향
     * @param topToLeft  좌측 대각
     * @param isFirst    첫행인지 체크
     */
    public static void check(int topToRight, int forward, int topToLeft, boolean isFirst) {
        //완료 체크 순환이 시작될 때 체크하여, 해당되면 바로 리턴한다.
        //퀸이 모드 들어왔다면 11---- 의 형태가 될 것이다.
        if (forward == done) {
            count++;
            return;
        }

        //충돌 체크. 실제 topToRight | topToLeft | forward 만으로도 충돌 체크가 되나, 이 후 연산을 위해 보수를 취한다.
        int poss = ~(topToRight | topToLeft | forward);

        //실제 충돌 체크 논리곱을 수행하여 비어 있는 자리가 있는 지를 체크 한다.
        while ((poss & done) != 0) {
            //이 후 연산을 위한 비트 값을 추출 한다.
            //0010, 0100 등과 같은 형태를 취하게 된다.
            int bit = poss & -poss;
            //좌우 대칭에 대한 연산을 줄이기 위한 체크. 절반까지 도달하면 카운트를 두배로 증가시킨다.
            //데칼코마니를 생각한다면 쉬울 것 이다.
            if (isFirst && half == bit) {
                //카운트 * 2
                count *= 2;
                //짝수 행은 더 이상 진행 할 필요가 없다.
                if (isPair) break;
            }
            //홀수를 위한 체크. 절반 이 후 홀수 행이 계산되면 더 이상 연산하지 않아도 된다.
            if (isFirst && half < bit) {
                break;
            }
            //위치 순한을 위한 증가.
            poss -= bit;
            //정면은 단지 현재 비트를 논리합하면 구할 수 있다.
            //좌우 대각 방향에 대해서는 1칸씩 쉬프트 연산으로 방향을 틀어버린다.
            //현재 메서드에 진입한 이상 더 이상 첫째 행이 아니므로, isFirst는 항상 false를 할당
            check((topToRight | bit) >> 1, forward | bit, (topToLeft | bit) << 1, false);
        }
    }

    /**
     * 메인
     *
     * @param args 아이고 의미없다.
     */
    public static void main(String[] args) {
        //스트링 빌더는 빠르다...
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < 14; i++) {
            s.append("n = ");
            s.append(i < 10 ? "0" + i : i);
            s.append(", solution count is ");
            s.append(execute(i));
            s.append(".\n");
        }
        System.out.print(s.toString());
    }
}