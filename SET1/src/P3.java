// Question 3/1:
//
// main() calls mystery(5,6)
//    mystery(5,6) calls mystery(4,4)
//        mystery(4,4) calls mystery(3,2)
//            mystery(3,2) calls mystery(2,0)
//                mystery(2,0) returns 2
//            mystery(3,2) returns 2 + 2, or 4
//        mystery(4,4) returns 4 + 4, or 8
//    mystery(5,6) returns 6 + 8, or 14
// mystery() returns 14
//
//
// Question 3/2:
// The value returned by mystery(5,6) is 14
//
// Question 3/3: There are total of 4 method frames on the stack when
// the base case is reached.
//
// Question 3/4:
// When a = 5, b = 5, the function will produce infinite recursion, as
// the base case will never end the function when (a - b) < 0;
//
//
//
