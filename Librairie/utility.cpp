#include <iostream>
#include <cstring>
#include <string>

using namespace std;
#include "utility.h"


string Utility::intToString(int i)
{
    string number;

    while(i > 0)
    {
        int digit = i%10;

        char digitToChar = digit + 48;//on convertis le nombre isolé en son symbol dans la table ascii

        i =  i / 10;

        number = digitToChar + number;
    }

    return(number);
}