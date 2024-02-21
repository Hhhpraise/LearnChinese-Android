-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: sql105.epizy.com
-- Generation Time: Apr 20, 2022 at 12:22 PM
-- Server version: 10.3.27-MariaDB
-- PHP Version: 7.2.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `epiz_31549482_app_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE `answers` (
  `id` int(11) NOT NULL,
  `q_id` int(11) NOT NULL,
  `A` varchar(255) NOT NULL,
  `B` varchar(255) NOT NULL,
  `C` varchar(255) NOT NULL,
  `D` varchar(255) NOT NULL,
  `q_ans` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `answers`
--

INSERT INTO `answers` (`id`, `q_id`, `A`, `B`, `C`, `D`, `q_ans`) VALUES
(1, 1, '你好', '很好', '真好', '一号', '你好'),
(2, 2, '看见', '再见', '拜拜', '百度', '再见'),
(3, 3, 'tommorrow', 'yesterday', 'next month', 'today', 'today'),
(4, 4, 'what is your name', 'when is it over', 'when is the next stop', 'none of the above', 'what is your name'),
(5, 5, '你好', '下午好', '早上好', '晚上好', '早上好'),
(6, 6, '谢谢你', '认识你', '对不起', '没关系', '谢谢你'),
(7, 7, 'wǒ hěn ɡāo xìnɡ rèn shí nǐ ', 'wǒ huì shuō yī dián ér hàn yǔ ', 'zhù nǐ hǎo yùn ', 'nǐ huì shuō yīnɡ yǔ mɑ ', 'wǒ huì shuō yī dián ér hàn yǔ '),
(8, 8, '一五', '五', '十五', '二十五', '十五'),
(9, 9, 'it\'s now ten o\'clock', 'I\'m going to bed', 'where are you now?', 'what time is it?', 'what time is it?'),
(10, 10, '明天见(mínɡ tiān jiàn )', '再见(zài jiàn )', '回头见(huí tóu jiàn )', '保重(bǎo zhònɡ )', '明天见(mínɡ tiān jiàn )'),
(11, 11, 'Train station', 'Airpot', 'Subway Station', 'Policce Station', 'Train station'),
(12, 12, 'To eat', 'To sleep', 'To think', 'To like', 'To like'),
(13, 13, 'wǒ yào qù wǒ de xué xiào ', 'wǒ yào shuì jiào ', 'wǒ yào qù yín hánɡ ', 'wǒ yào qù chī fàn ', 'wǒ yào qù yín hánɡ '),
(14, 14, 'The car is too slow', 'Tianjin is too far', 'The weather is too hot', 'I can\'t speak chinese', 'The weather is too hot'),
(15, 15, '', '', '', '', ''),
(16, 16, '', '', '', '', ''),
(17, 17, '', '', '', '', ''),
(18, 18, '', '', '', '', ''),
(19, 19, '', '', '', '', ''),
(20, 20, '', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `audio_answer`
--

CREATE TABLE `audio_answer` (
  `id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `A` varchar(255) NOT NULL,
  `B` varchar(255) NOT NULL,
  `C` varchar(255) NOT NULL,
  `D` varchar(255) NOT NULL,
  `ans` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `audio_answer`
--

INSERT INTO `audio_answer` (`id`, `question_id`, `A`, `B`, `C`, `D`, `ans`) VALUES
(1, 1, '一号，八号(yí hào ， bá hào  )', '八号,八号(bá hào, bá hào  )', '八号,一号( bá hào , yí hào )', '一号,一号(yí hào, yí hào )', '一号，八号(yí hào ， bá hào  )'),
(2, 2, '你好,你好( nǐ hǎo, nǐ hǎo )', '不好,你好\n(bù hǎo, nǐ hǎo )', '不好,不好\n(bù hǎo, bù hǎo )', '你好，不好\n(nǐ hǎo ， bù hǎo )', '你好，不好\n(nǐ hǎo ， bù hǎo )'),
(3, 3, '地图，地图 (dì tú ， dì tú )\n', '地图，意图 (dì tú ， yì tú )\n', '意图, 意图 (yì tú,  yì tú )\n', '\n意图, 地图 (yì tú,  dì tú )\n', '地图，意图 (dì tú ， yì tú )'),
(4, 4, '白码, 大码 (bái mǎ,  dà mǎ )\n', '大码，大码 (dà mǎ ， dà mǎ )\n', '白码, 白码 (bái mǎ,  bái mǎ )\n', '大码，白码 (dà mǎ ， bái mǎ )\n', '大码，白码 (dà mǎ ， bái mǎ )\n'),
(5, 5, '抬头，带头 (tái tóu ， dài tóu )\n', '带头，抬头（dài tóu ， tái tóu ）\n', '带头，带头 (dài tóu ， dài tóu ) \n', '抬头，抬头 (tái tóu ， tái tóu )\n', '带头，抬头（dài tóu ， tái tóu ）\n'),
(6, 6, '塔楼, 大楼 (tǎ lóu,  dà lóu )\n', '塔楼, 塔楼 (tǎ lóu,  tǎ lóu )\n', '大楼，塔楼 (dà lóu ， tǎ lóu )\n', '大楼，大楼 (dà lóu ， dà lóu )\n', '大楼，塔楼 (dà lóu ， tǎ lóu )\n'),
(7, 7, '大于，大于 (dà yú ， dà yú )\n', '大于，大玉 (dà yú ， dà yù )\n', '大玉, 大于 (dà yù,  dà yú )\n', '大玉, 大玉 (dà yù,  dà yù )\n', '大于，大玉 (dà yú ， dà yù )\n'),
(8, 8, '客服，刻苦 (kè fú ， kè kǔ )\n', '刻苦, 客服 (kè kǔ,  kè fú )\n', '客服，客服 (kè fú ， kè fú )\n', '刻苦, 刻苦 (kè kǔ,  kè kǔ )\n', '客服，刻苦 (kè fú ， kè kǔ )\n'),
(9, 9, '语法，语法 (yú fǎ ， yú fǎ )\n', '理发, 语法 (lǐ fà,  yú fǎ )\n', '理发, 理发 (lǐ fà,  lǐ fà )\n', '语法，理发 (yú fǎ ， lǐ fà )\n', '语法，理发 (yú fǎ ， lǐ fà )\n'),
(10, 10, '美好，没来 (méi hǎo ， méi lái )\n', '没来, 没来 (méi lái,  méi lái )\n', '美好，美好 (méi hǎo ， méi hǎo )\n', '没来, 美好 (méi lái,  méi hǎo )\n', '美好，没来 (méi hǎo ， méi lái )\n'),
(11, 11, '动作, 工作 (dònɡ zuò,  ɡōnɡ zuò  )', '工作，工作 (ɡōnɡ zuò ， ɡōnɡ zuò )\n', '工作，动作 (ɡōnɡ zuò ， dònɡ zuò )', '动作, 动作 (dònɡ zuò,  dònɡ zuò )', '工作，动作 (ɡōnɡ zuò ， dònɡ zuò )'),
(12, 12, '孩子, 还是 (hái zi,  hái shì )', '还是，孩子 (hái shì ， hái zi )', '孩子, 孩子 (hái zi,  hái zi )\n', '还是，还是 (hái shì ， hái shì )\n', '还是，孩子 (hái shì ， hái zi )'),
(13, 13, '汽车，骑车 (qì chē ， qí chē )', '骑车, 骑车 (qí chē,  qí chē )', '骑车, 汽车 (qí chē,  qì chē )', '汽车，汽车 (qì chē ， qì chē )', '汽车，骑车 (qì chē ， qí chē )'),
(14, 14, '颜色, 颜色 (yán sè,  yán sè)\n', '眼色, 颜色 (yǎn sè,  yán sè)', '眼色, 眼色 (yǎn sè,  yǎn sè)', '颜色，眼色 (yán sè ， yǎn sè)', '颜色，眼色 (yán sè ， yǎn sè)'),
(15, 15, '火车, 火车 (huǒ chē,  huǒ chē )\n', '或者，火车 (huò zhě ， huǒ chē )\n', '火车, 或者 (huǒ chē,  huò zhě )\n', '或者，或者 (huò zhě ， huò zhě )\n', '或者，火车 (huò zhě ， huǒ chē )\n'),
(16, 16, '最近，税金 (zuì jìn ， shuì jīn )\n', '最近，最近 (zuì jìn ， zuì jìn )\n', '税金, 最近 (shuì jīn,  zuì jìn )\n', '税金, 税金 (shuì jīn,  shuì jīn )\n', '最近，税金 (zuì jìn ， shuì jīn )\n'),
(17, 17, '读书，图书 (dú shū ， tú shū )\n', '图书, 读书 (tú shū,  dú shū )\n', '读书，读书 (dú shū ， dú shū )\n', '读书，图书 (dú shū ， tú shū )\n', '读书，图书 (dú shū ， tú shū )\n'),
(18, 18, '写书，结束 （xiě shū ， jié shù ）', '结束，写书 （jié shù ， xiě shū）', '写书，写书 （xiě shū ， xiě shū ）', '结束，结束 （jié shù ， jié shù ）', '结束，写书 （jié shù ， xiě shū）'),
(19, 19, '叫人，敲门 （jiào rén ， qiāo mén ）', '敲门，敲门 （qiāo mén ， qiāo mén ）', '敲门，叫人 （qiāo mén ， jiào rén ）', '叫人，叫人 （jiào rén ， jiào rén ）', '敲门，叫人 （qiāo mén ， jiào rén ）'),
(20, 20, '补课，不可 （bǔ kè ， bù kě ）\n', '不可，补课 （bù kě ， bǔ kè ）\n', '补课，补课 （bǔ kè ， bǔ kè ）\n', '不可，不可 （bù kě ， bù kě ）\n', '不可，补课 （bù kě ， bǔ kè ）\n');

-- --------------------------------------------------------

--
-- Table structure for table `audio_level`
--

CREATE TABLE `audio_level` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `audio_level`
--

INSERT INTO `audio_level` (`id`, `name`) VALUES
(4, 'Level One'),
(5, 'Level Two');

-- --------------------------------------------------------

--
-- Table structure for table `audio_question`
--

CREATE TABLE `audio_question` (
  `id` int(11) NOT NULL,
  `level_id` int(11) NOT NULL,
  `word` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `audio_question`
--

INSERT INTO `audio_question` (`id`, `level_id`, `word`) VALUES
(1, 1, '一号，八号'),
(2, 1, '你好，不好'),
(3, 1, '地图，意图'),
(4, 1, '大码，白码'),
(5, 1, '带头，抬头'),
(6, 1, '大楼，塔楼'),
(7, 1, '大于，大玉'),
(8, 1, '客服，刻苦'),
(9, 1, '语法，理发'),
(10, 1, '美好，没来'),
(11, 2, '工作，动作'),
(12, 2, '还是，孩子'),
(13, 2, '汽车，骑车'),
(14, 2, '颜色，眼色'),
(15, 2, '或者，火车'),
(16, 2, '最近，税金'),
(17, 2, '读书，图书'),
(18, 2, '结束，写书'),
(19, 2, '敲门，叫人'),
(20, 2, '不可，补课'),
(21, 3, '客气，可气'),
(22, 3, '不过，补过'),
(23, 3, '他们，大门'),
(24, 3, '教授，教书'),
(25, 3, '护士，忽视'),
(26, 3, '经理，经历'),
(27, 3, '大夫，态度'),
(28, 3, '炉石，历史'),
(29, 3, '大学，大雪'),
(30, 3, '语言，预言');

-- --------------------------------------------------------

--
-- Table structure for table `culture_details`
--

CREATE TABLE `culture_details` (
  `id` int(11) NOT NULL,
  `culture_id` int(11) NOT NULL,
  `text` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `culture_details`
--

INSERT INTO `culture_details` (`id`, `culture_id`, `text`) VALUES
(1, 1, 'Spring Festival, or the Chinese New Year is the most important traditional festival in China. It is a happy , merry day when family members have a reunion . Every year during the Spring Festival, people away from home will return home to celebrate the festival with their families. Together they paste antithetical couplets , light off firecrackers, some eat dumplings, and watch the Spring Festival Gala happily and cheerfully. Kids love the Spring Festival best because they can get money from their elders as gits. \n<p></p>\nThe Chinese New Year is associated with several myths and customs. The festival was traditionally a time to honor deities as well as ancestors. Within China, regional customs and traditions concerning the celebration of the New Year vary widely, and the evening preceding the New Year\'s Day is frequently regarded as an occasion for Chinese families to gather for the annual reunion dinner. It is also traditional for every family to thoroughly clean their house, in order to sweep away any ill fortune and to make way for incoming good luck. Another custom is the decoration of windows and doors with red paper-cuts and couplets. Popular themes among these paper-cuts and couplets include good fortune or happiness, wealth, and longevity. Other activities include lighting firecrackers and giving money in red paper envelopes.\n\n'),
(2, 2, 'Jiaozi (Chinese: 饺子) are Chinese dumplings commonly eaten in China and other parts of East Asia. Jiaozi are folded to resemble Chinese sycee and have great cultural significance attached to them within China. Jiaozi are one of the major dishes eaten during the Chinese New Year throughout the entire country and eaten all year round in the northern provinces. Though considered part of Chinese cuisine, jiaozi are popular in other parts of East Asia and in the Western world, where a fried variety is sometimes called potstickers.\n<p></p>\nJiaozi typically consist of a ground meat and/or vegetable filling wrapped into a thinly rolled piece of dough, which is then sealed by pressing the edges together. Finished jiaozi can be boiled (shuǐ jiǎo), steamed (zhēng jiǎo), pan fried (jiān jiǎo), or deep fried (zhà jiǎo), and are traditionally served with a black vinegar and sesame oil dip. They can also be served in a soup (tāng jiǎo).\n<p></p>\n\nChinese dumplings (jiaozi) may be divided into various types depending on how they are cooked: <br>\n1.	Boiled dumplings (simplified Chinese: 水饺; traditional Chinese: 水餃; pinyin: shuǐjiǎo; lit. \'water dumpling\') <br>\n2.	Steamed dumplings (simplified Chinese: 蒸饺; traditional Chinese: 蒸餃; pinyin: zhēngjiǎo; lit. \'steam dumpling\') <br>\n3.	Pan-fried dumplings (simplified Chinese: 煎饺; traditional Chinese: 煎餃; pinyin: jiānjiǎo; lit. \'dry-fried dumplings\'), and (simplified Chinese: 锅贴; traditional Chinese: 鍋貼; pinyin: guōtiē; lit. \'pan stick\') also referred to as \"pot-stickers\"<br>\n4.	Deep fried dumplings simplified Chinese: 炸饺; traditional Chinese: 炸餃; pinyin: zhà jiǎo; lit. \'deep-fried dumplings\') <br>\n5.	Soup dumplings (simplified Chinese: 汤饺; traditional Chinese: 湯餃; pinyin: tāngjiǎo; lit. \'soup dumpling\')\n<br>\nDumplings that use egg rather than dough to wrap the filling are called \"egg dumplings\" (simplified Chinese: 蛋饺  pinyin: dànjiǎo; lit. \'egg dumpling\'). <br>\nPan-fried dumplings can be joined together by a brown, crispy lattice base created by pouring a flour and water mix into the pan at the end of cooking. In Chinese, this is known as \"frost\" or \"ice crystal\" (冰花). The dumplings can also be joined together with an egg base which is topped with green onion and sesame seeds.\n'),
(3, 3, 'A red envelope, red packet or red pocket (simplified Chinese: 红包; traditional Chinese: 紅包; pinyin: hóngbāo) is a monetary gift given during holidays or for special occasions such as a wedding, a graduation, or the birth of a baby. Although the red envelope was popularized by Chinese traditions, other cultures also share similar traditional customs. The red packet is also called \"money warding off old age\" (压岁钱; yāsuì qián) for Chinese New Year.\n<p></p>\nRed envelopes are gifts presented at social and family gatherings such as weddings or holidays such as Chinese New Year. The red color of the envelope symbolizes good luck and is a symbol to ward off evil spirits. It is also gifted when a person is visiting as a gesture of kindness for visiting. The act of requesting red packets is normally called tao hongbao (Chinese: 讨红包; pinyin: tǎo hóngbāo) or yao lishi (Chinese: 要利是; pinyin: yào lì shì), and in the south of China, dou li shi (Chinese: 逗利是; pinyin: dòu lì shì ). Red envelopes are usually given out to the younger generation who are normally still in school or unmarried.\n<p></p>\n\nThe amount of money contained in the envelope usually ends with an even digit, in accordance with Chinese beliefs; odd-numbered money gifts are traditionally associated with funerals. The exception being the number 9 as the pronunciation of nine (Chinese: 九) is homophonous to the word long (久) and is the largest digit. Still in some regions of China and in its diaspora community, odd numbers are favored for weddings because they are difficult to divide. There is also a widespread tradition that money should not be given in fours, or the number four should not appear in the amount, such as in 40, 400 and 444, as the pronunciation of the word four (四) is homophonous to the word death (死). When giving money, new crispy bills are normally given instead of old dirty bills. It is common to see long queues outside of banks before Chinese New Year with people waiting to get new bills.\n'),
(4, 4, 'When visiting someone’s home, we usually bring a gift. Remember not all gifts are appropriate.  In China, some articles cannot be given as gifts, such as umbrellas, shoes and clocks. The pronunciation of the Chinese character for “umbrella “ is similar to that of the character for “separation”, so giving an umbrella means to separate from the person. The character for “shoe” sounds the same as that for “evil”, which bodies ill. The Chinese world for “giving a clock” sounds exactly the same as that for “attending upon a dying person”. What taboos do you have in your country regarding giving gifts?'),
(5, 5, 'Chinese people prefer round tables when dining, no matter at home or in a restaurant, so that all can sit face-to-face. Seated in a restaurant, the host has the main guest on his right hand and the second-most important guest on his left. The seat opposite to the host, where dishes are served , usually cannot be offered to a guest.\nOne can tell the host from the guest through the dishes also. If there is a fish on the table, the head of the fish should point to the most important guest to show the host’s respect for the guest.\n'),
(6, 6, 'Chinese people love drinking tea. Tea is both delicious and beneficial to our health. It can help people refresh themselves, defer aging , prevent diseases and even lose weight. There are a variety of Chinese tea , non-fermented green tea, half-fermented green tea and scented tea. People drink different kinds of tea in different seasons. Generally speaking, people drink scented tea in spring, non-fermented green tea in summer, half-fermented green tea in autumn and black tea in winter.\n<p></p>\nAccording to Chinese tradition, members of the younger generation should show their respect to members of the older generation by offering a cup of tea.[1] Inviting their elders to restaurants for tea is a traditional holiday activity. Newly married couples serve tea to their elder family members. In the past, people of a lower social class served tea to the upper class in society. Today, with the increasing liberalization of Chinese society, this rule and its connotations have become blurred.\n'),
(7, 7, 'Westerners eat cakes on their birthdays , while by tradition, Chinese people eat noodles. The noodles , being long , stand for “longevity”, which is why birthday noodles are also called longevity noodles. What do people eat on their birthdays in your country?'),
(8, 8, 'Chinese martial arts, often called by the umbrella terms kung fu (/ˈkʌŋ ˈfuː/; Chinese: 功夫; pinyin: gōngfu; ) or wushu (武术; wǔshù), are multiple fighting styles that have developed over the centuries in Greater China. These fighting styles are often classified according to common traits, identified as \"families\" of martial arts. Examples of such traits include Shaolinquan (少林拳) physical exercises involving All Other Animals (五形) mimicry or training methods inspired by Old Chinese philosophies, religions and legends. Styles that focus on qi manipulation are called internal (内家拳; nèijiāquán), while others that concentrate on improving muscle and cardiovascular fitness are called external (外家拳; wàijiāquán). Geographical association, as in northern (北拳; běiquán) and southern (南拳; nánquán), is another popular classification method.\n<p></p>\nWushu literally means \"martial art\". It is formed from the two Chinese characters 武術: 武 (wǔ), meaning \"martial\" or \"military\" and 術 or 术 (shù), which translates into \"art\", \"discipline\", \"skill\" or \"method\". The term wushu has also become the name for the modern sport of wushu, an exhibition and full-contact sport of bare-handed and weapon forms (套路), adapted and judged to a set of aesthetic criteria for points developed since 1949 in the People\'s Republic of China.\n<p></p>\nthe term kung fu refers to any skill that is acquired through learning or practice. It is a compound word composed of the words 功 (gōng) meaning \"work\", \"achievement\", or \"merit\", and 夫 (fū) which is a particle or nominal suffix with diverse meanings.\n'),
(9, 9, 'In China apart form modern sports like jogging, working out in gyms , and playing balls, some traditional sports handed down from old tines are also practiced as part of people’s daily exercise, such as flying kites, kicking shuttlecocks, playing diablolos, and practicing tainjiquan. Can you do these sports? What traditional sports do you have in your country?\n'),
(10, 10, 'In traditional Chinese culture, age isn’t considered privacy. It is a topic often brought up in social occasions. Nevertheless, different ways are employed to ask about the age of different people. For kids younger than 10, people ask (how old are you ?) ; for a young person or someone of one’s own age, you may ask “你今年多大了（How old are you?)” ; for an elder person, however, one should use “你今年多大年纪了（What is your age?)“ to show respect.'),
(11, 11, 'A Chinese name starts with the family names and ends with the given names , for example , in the names “李月“，”谢朋“ , and  “王方“ , “李“，”王“ and “谢”are the family names and “月”，“朋”and “方” are the given names.\nThere are over 5000 Chinese family names, among which more than 200 are commonly seen “张“，“王”，“李”are the most common ones. Such family names as have only one character are known as single-character surnames. Most Chinese people have a single-character surname. There are surnames with two or more characters also, which are called compound-character surnames.\nA person can be addressed with his/her family name followed by his/her job or profession. For instance, “李月“  is a teacher, so we can call her “李老师“。\n'),
(12, 12, 'In China, there are two kinds of common communication tools-telephones and cell phones. A telephone number usually has 7-8 digits, for example, 2027816, 82304156. Telephone numbers in different regions have different numbers of digits. Cell phone numbers, however always have 11 digits regardless of region , as in 13576983311. Since a cell phone number has many digits,it is read with pause following the pattern “3-4-4”, such as 139-0107-886. “1” is read as “yao”in a phone number.'),
(13, 13, 'Chinese calligraphy is the writing of Chinese characters as an art form, combining purely visual art and interpretation of the literary meaning. This type of expression has been widely practiced in China and has been generally held in high esteem across East Asia. Calligraphy is considered one of the four most-sought skills and hobbies of ancient Chinese literati, along with playing stringed musical instruments, the board game \"Go\", and painting. There are some general standardizations of the various styles of calligraphy in this tradition. Chinese calligraphy and ink and wash painting are closely related: they are accomplished using similar tools and techniques, and have a long history of shared artistry. Distinguishing features of Chinese painting and calligraphy include an emphasis on motion charged with dynamic life. According to Stanley-Baker, \"Calligraphy is sheer life experienced through energy in motion that is registered as traces on silk or paper, with time and rhythm in shifting space its main ingredients.\" Calligraphy has also led to the development of many forms of art in China, including seal carving, ornate paperweights, and inkstones.\n<p></p>\nIn China, calligraphy is referred to as shūfǎ or fǎshū (书法, 法书), literally \'way/method/law of writing\'; shodō (书道) in Japan (\'way/principle of writing\'); and seoye (서예; 书艺) in Korea (\'skill/criterion of writing\'[3]);thư pháp (书法) in Vietnam (\'handwriting art\').\n<p></p>\n\nChinese calligraphy appreciated more or only for its aesthetic quality has a long tradition, and is today regarded as one of the arts (Chinese 藝術/艺术 pinyin: yìshù, a relatively recent word in Chinese) in the countries where it is practised. Chinese calligraphy focuses not only on methods of writing but also on cultivating one\'s character (人品) and taught as a pursuit (－书法; pinyin: shūfǎ, rules of writing Han characters).\n<p></p>\n\nChinese calligraphy used to be popular in China, Taiwan, Korea , Vietnam and Hongkong. In Taiwan, students were requested to write Chinese calligraphy starting from primary school all the way to junior high school on weekly basis at least to year 1980. As young generations are \"typing\" more often than \"writing\", when PC, tablets and mobile phones became the major communication channels, Chinese calligraphy becomes purely art.\n'),
(14, 14, 'Chinese painting (simplified Chinese: 中国画; pinyin: Zhōngguó huà) is one of the oldest continuous artistic traditions in the world. Painting in the traditional style is known today in Chinese as guó huà (simplified Chinese: 国画  meaning \"national painting\" or \"native painting\", as opposed to Western styles of art which became popular in China in the 20th century. It is also called danqing (Chinese: 丹青; pinyin: dān qīng). Traditional painting involves essentially the same techniques as calligraphy and is done with a brush dipped in black ink or coloured pigments; oils are not used. As with calligraphy, the most popular materials on which paintings are made are paper and silk. The finished work can be mounted on scrolls, such as hanging scrolls or handscrolls. Traditional painting can also be done on album sheets, walls, lacquerware, folding screens, and other media.\n<p></p>\nThe two main techniques in Chinese painting are:\n<br>\n\n<b>Gongbi (工笔) </b>\n<br> meaning \"meticulous\", uses highly detailed brushstrokes that delimit details very precisely. It is often highly colored and usually depicts figural or narrative subjects. It is often practiced by artists working for the royal court or in independent workshops.\n<br>\n<b>Ink and wash painting</b>\n<br> in Chinese shuǐ-mò (水墨, \"water and ink\") also loosely termed watercolor or brush painting, and also known as \"literati painting\", as it was one of the \"four arts\" of the Chinese Scholar-official class.[1] In theory this was an art practiced by gentlemen, a distinction that begins to be made in writings on art from the Song dynasty, though in fact the careers of leading exponents could benefit considerably.[2] This style is also referred to as \"xieyi\" (写意) or freehand style.\n');

-- --------------------------------------------------------

--
-- Table structure for table `culture_table`
--

CREATE TABLE `culture_table` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `img` varchar(255) NOT NULL,
  `type_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `culture_table`
--

INSERT INTO `culture_table` (`id`, `name`, `img`, `type_id`) VALUES
(1, 'Spring Festival', 'https://images.unsplash.com/photo-1614130020598-093435d7f1a2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80', 1),
(2, 'Jiaozi', 'https://images.pexels.com/photos/7364181/pexels-photo-7364181.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 1),
(3, 'Hongbao', 'https://images.pexels.com/photos/7363702/pexels-photo-7363702.jpeg?auto=compress&cs=tinysrgb&w=1600', 1),
(4, 'Taboo gifts in china', 'https://images.unsplash.com/photo-1512909161545-dbcdbbe06d7e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80', 1),
(5, 'Chinese Table Manners', 'https://tse1-mm.cn.bing.net/th/id/R-C.f9732c7e3c651080e4c1af019046f350?rik=m0Hx0NwlyF84BA&riu=http%3a%2f%2fconfuciusmag.com%2fwp-content%2fuploads%2f2015%2f07%2f28_chinese_dining_etiquette_03.jpg&ehk=aYf%2bj3OBPqGQja4f8RAHSQeghhaCmzI3cjaFKZ0RE0E%3d&risl=&', 2),
(6, 'Chinese Tea Culture', 'https://images.pexels.com/photos/230477/pexels-photo-230477.jpeg?auto=compress&cs=tinysrgb&w=1600', 2),
(7, 'What Chinese people eat on thier birthdays', 'https://images.pexels.com/photos/7594058/pexels-photo-7594058.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 2),
(8, 'Kungfu', 'https://images.pexels.com/photos/7045738/pexels-photo-7045738.jpeg', 4),
(9, 'Traditional Sports', 'https://images.pexels.com/photos/40751/running-runner-long-distance-fitness-40751.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 4),
(10, 'Ways of Asking a Chinese person\'s age', 'https://images.pexels.com/photos/7651545/pexels-photo-7651545.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 5),
(11, 'Features of Chinese People\'s Names', 'https://res.klook.com/images/fl_lossy.progressive,q_65/c_fill,w_1295,h_720,f_auto/w_80,x_15,y_15,g_south_west,l_klook_water/activities/rxzvnba6ljsqqbncnbl9/ChineseCalligraphyClassatAuraArtinHongKong.jpg', 5),
(12, 'Common Communication tool of Chinese people', 'https://images.unsplash.com/photo-1589264431429-5ced194112ef?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80', 5),
(13, 'Caligraphy', 'https://res.klook.com/images/fl_lossy.progressive,q_65/c_fill,w_1295,h_720,f_auto/w_80,x_15,y_15,g_south_west,l_klook_water/activities/rxzvnba6ljsqqbncnbl9/ChineseCalligraphyClassatAuraArtinHongKong.jpg', 3),
(14, 'Chinese Painting', 'https://tse1-mm.cn.bing.net/th/id/R-C.efc162ec638b0dbb9b4ffb1419fa6579?rik=%2bHRZBOy0wfSGKg&riu=http%3a%2f%2fwww.artisoo.com%2fimages%2fchinesepainting1%2fcnag220937.jpg&ehk=rBI6QfYl5URj%2fb3t%2fiTlkyWAMdm%2blRQEbBl674Q0qgw%3d&risl=&pid=ImgRaw&r=0', 3);

-- --------------------------------------------------------

--
-- Table structure for table `culture_type`
--

CREATE TABLE `culture_type` (
  `id` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `img` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `culture_type`
--

INSERT INTO `culture_type` (`id`, `type`, `img`) VALUES
(1, 'Festivals', 'https://images.unsplash.com/photo-1613625695262-98bceeda4bc0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80'),
(2, 'Foods', 'https://images.unsplash.com/flagged/photo-1556742524-750f2ab99913?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80'),
(3, 'Art', 'https://images.unsplash.com/photo-1611387729651-a017cc1162bc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=735&q=80'),
(4, 'Martial Arts', 'https://images.pexels.com/photos/7045738/pexels-photo-7045738.jpeg'),
(5, 'General', 'https://images.unsplash.com/photo-1524548209323-6fb4a0d4a4a3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=688&q=80');

-- --------------------------------------------------------

--
-- Table structure for table `dialogue`
--

CREATE TABLE `dialogue` (
  `id` int(11) NOT NULL,
  `video_id` int(11) NOT NULL,
  `A` longtext NOT NULL,
  `B` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dialogue`
--

INSERT INTO `dialogue` (`id`, `video_id`, `A`, `B`) VALUES
(1, 1, '（ kāi mén ） xiǎo ménɡ shì nǐ yɑ ！ kuài qǐnɡ jìn ， háo jiǔ bú jiàn 。 zuì jìn zěn me yànɡ ？<br>\n(Door opens) Xiao Meng its you! Please come in, I haven\'t seen you for a long time. How is everything?', 'hái ké yǐ ， nǐ ne ？<br>\nOkay, what about you?'),
(2, 1, 'wǒ hěn hǎo 。 nǐ ɡōnɡ zuò mánɡ mɑ ？<br>\nI am fine. Are you busy at work?', 'bú tài mánɡ ， ɡēn yǐ qián yí yànɡ 。 duì le ， nǐ de jiā rén zuì jìn zěn me yànɡ ？ <br>\nNot too busy, as before. By the way, how has your family been doing lately?'),
(3, 1, 'tā men dōu hěn hǎo 。 āi ？ méi jì cuò de huà ， jīn tiān shì nǐ de shēng rì ba？<br>\nThey are all good. ay? If I remember correctly, today is your birthday, right?', 'shì de <br>\nyes'),
(4, 1, 'tài qiǎo le ， zhù nǐ shēnɡ rì kuài lè ！<br>\nWhat a coincidence, happy birthday to you!', ' xiè xiè ！<br>\nThanks!'),
(5, 3, ' nǐ nǎ lǐ bù shū fu ？ <br>\nWhere are you uncomfortable?', 'wǒ kě nénɡ ɡǎn mào le 。 \n<br>\nI may have a cold.'),
(6, 3, ' yǒu shén me zhènɡ zhuànɡ ？<br>\nWhat are the symptoms?', 'tóu ténɡ 、 hóu lónɡ ɡān 、 hái yóu dián ér yūn 。 <br>\nHeadache, dry throat, and a little dizzy'),
(7, 3, 'fā shāo mɑ ？ <br>\nHave a fever?', 'kě nénɡ yǒu yì diǎn 。 <br>\nMaybe a little.'),
(8, 3, 'nà xiān qù liánɡ tǐ wēn ， rán hòu qù yàn xuè 。 （ jié ɡuǒ chū lái le ） <br>\nThen go to the temperature, and then go to the blood test. (Results are out)', ''),
(9, 3, 'jié ɡuǒ chū lái le ， shì zhònɡ ɡǎn mào 。 xiān dǎ ɡè zhēn ， zài ɡěi nǐ kāi yì zhōu de ɡǎn mào yào ， zhè yànɡ hǎo dé kuài 。 huí jiā zhī hòu chī qīnɡ dàn de ， zhù yì xiū xi 。<br>\nThe result came out, it was a heavy cold. Get an injection first, and then give you a week\'s worth of cold medicine, which will get better quickly. Eat light after returning home and pay attention to rest.', 'hǎo de ， xiè xiè dài fu 。<br>\nOkay, thank you Doctor.'),
(10, 2, 'tónɡ xué ， xiǎnɡ chī dián ér shén me ？ <br>\nClassmates, what do you want to eat?', 'wǒ yào yí fèn kuài cān 。 yào tǔ dòu 、 shǔ tiáo hé hàn bǎo ， xiè xiè 。<br>\nI want a fast food. For potatoes, fries and burgers, thank you.'),
(11, 2, 'wǒ yào hé tā yí yànɡ de ， xiè xiè 。 <br>\nI want that also , thank you.', ' nǐ kàn ！ nà bú shì nǐ nan pénɡ you mɑ ？ <br>\nYou see! Isn\'t that your boyfriend?'),
(12, 2, 'shì ā ！ wǒ men hé tā yì qǐ chī fàn bɑ ！ nǐ yǒu nǚ pénɡ you mɑ ？ <br>\nOh, yes! Let\'s have dinner with her! Do you have a girlfriend?', 'wǒ yǒu 。 <br>\nI do'),
(13, 2, 'tā zhǎnɡ dé piào liɑnɡ mɑ ？ <br>\nIs she pretty?', 'fēi chánɡ piào liɑnɡ 。 dà dà de yǎn jinɡ ， chánɡ tóu fɑ ， 1 mǐ 75 ， ɡè zi hěn ɡāo 。 yě hěn xǐ huɑn huà huà 。<br>\nVery pretty. Big eyes, long hair, 1 meter 75, very tall.she also likes to draw.'),
(14, 2, 'yǒu shí jiān wǒ men sì ɡè yì qǐ chī fàn ！<br>\nwhen there is time the four of us could eat together!', 'hǎo ā <br>\nokay');

-- --------------------------------------------------------

--
-- Table structure for table `finals`
--

CREATE TABLE `finals` (
  `id` int(11) NOT NULL,
  `text` varchar(255) NOT NULL,
  `sound` varchar(255) NOT NULL,
  `final_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `finals`
--

INSERT INTO `finals` (`id`, `text`, `sound`, `final_id`) VALUES
(1, 'a', '啊', 1),
(2, 'o', '喔', 1),
(3, 'e', '鹅', 1),
(4, 'i', '衣', 1),
(5, 'u', '乌', 1),
(6, 'ü', '鱼', 1),
(7, 'ai', '爱', 2),
(8, 'ei', '诶', 2),
(9, 'ui', '喂', 2),
(10, 'ao', '凹', 2),
(11, 'ou', '欧', 2),
(12, 'iu', '优', 2),
(13, 'ia', '呀', 2),
(14, 'ie', '耶', 2),
(15, 'iao', '腰', 2),
(16, 'ua', '蛙', 2),
(17, 'uai', '歪', 2),
(18, 'üe', '月', 2),
(19, 'uo', '窝', 2),
(20, 'an', '安', 3),
(21, 'ang', '昂', 3),
(22, 'ong', '红', 3),
(23, 'en', '恩', 3),
(24, 'eng', '亨', 3),
(25, 'in', '因', 3),
(26, 'ing', '英', 3),
(27, 'ian', '烟', 3),
(28, 'iang', '央', 3),
(29, 'iong', '雍', 3),
(30, 'un', '文', 3),
(31, 'uan', '弯', 3),
(32, 'uang', '汪', 3),
(33, 'ueng', '翁', 3),
(34, 'ün', '云', 3),
(35, 'üan', '冤', 3);

-- --------------------------------------------------------

--
-- Table structure for table `initials`
--

CREATE TABLE `initials` (
  `id` int(11) NOT NULL,
  `text` varchar(100) NOT NULL,
  `sound` varchar(255) NOT NULL,
  `class_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `initials`
--

INSERT INTO `initials` (`id`, `text`, `sound`, `class_id`) VALUES
(1, 'b', '播', 1),
(2, 'p', '泼', 1),
(3, 'm', '摸', 1),
(4, 'f', '佛', 1),
(5, 'd', '得', 1),
(6, 't', '特', 1),
(7, 'n', '讷', 1),
(8, 'l', '勒', 1),
(9, 'g', '鸽', 1),
(10, 'k', '蝌', 1),
(11, 'h', '喝', 1),
(12, 'j', '鸡', 1),
(13, 'q', '旗', 1),
(14, 'x', '西', 1),
(15, 'zh', '蜘', 1),
(16, 'ch', '吃', 1),
(17, 'sh', '狮', 1),
(18, 'r', '日', 1),
(19, 'z', '字', 1),
(20, 'c', '刺', 1),
(21, 's', '丝', 1),
(22, 'y', '衣', 1),
(23, 'w', '乌', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pinyin_class`
--

CREATE TABLE `pinyin_class` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `img` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pinyin_class`
--

INSERT INTO `pinyin_class` (`id`, `name`, `img`) VALUES
(1, 'Initials', 'https://cdn.pixabay.com/photo/2014/10/04/16/55/wooden-cubes-473703_960_720.jpg'),
(2, 'Finals', 'https://cdn.pixabay.com/photo/2014/10/04/16/55/wooden-cubes-473703_960_720.jpg'),
(3, 'Tones', 'https://www.linkpicture.com/q/tone_2.png');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `id` int(11) NOT NULL,
  `level_id` int(11) NOT NULL,
  `question` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `level_id`, `question`) VALUES
(1, 1, 'How do you say \"Hello\" in Chinese?'),
(2, 1, 'Good bye in chinese:'),
(3, 1, '今天 means what?'),
(4, 1, '你叫什么名字 ：'),
(5, 1, 'Good morning'),
(6, 1, 'Thank you'),
(7, 1, 'I can speak a little Chinese'),
(8, 1, 'Chinese character for 15?'),
(9, 1, '现在什么时候'),
(10, 1, 'See you tomorrow'),
(11, 2, 'huǒ chē zhàn :'),
(12, 2, '喜欢 ( xǐ huɑn )'),
(13, 2, 'I have to go to the bank:'),
(14, 2, '天气太热了 ( tiān qì tài rè le )'),
(15, 2, 'I have to go to school'),
(16, 2, 'How tall are you?'),
(17, 2, '生日快乐！'),
(18, 2, 'he is  20 years old：'),
(19, 2, 'Where are you going?'),
(20, 2, 'I\'m very happy');

-- --------------------------------------------------------

--
-- Table structure for table `quiz_level`
--

CREATE TABLE `quiz_level` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `img` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `quiz_level`
--

INSERT INTO `quiz_level` (`id`, `name`, `img`) VALUES
(1, 'Level One', 'https://images.unsplash.com/photo-1434030216411-0b793f4b4173?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80'),
(2, 'Level Two', 'https://images.unsplash.com/photo-1434030216411-0b793f4b4173?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`) VALUES
(1, 'praise', 'praisearowolo33@gmail.com', '$2y$10$nGTysCu6lrsyEdruVLx.Le2IRNVcFUYJPVoLorR0sSZ0idUHL6ClC');

-- --------------------------------------------------------

--
-- Table structure for table `videos`
--

CREATE TABLE `videos` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `duration` varchar(255) NOT NULL,
  `link` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `videos`
--

INSERT INTO `videos` (`id`, `name`, `duration`, `link`) VALUES
(1, 'Speaking with friend', '1', 'https://webplus-cn-hangzhou-s-5df9f1f63c3f2876a5c3f846.oss-cn-hangzhou.aliyuncs.com/1649763566035368.mp4'),
(2, 'At the cafetaria', '1', 'https://webplus-cn-hangzhou-s-5df9f1f63c3f2876a5c3f846.oss-cn-hangzhou.aliyuncs.com/1649763566034256.mp4'),
(3, 'At the hospital', '2', 'https://webplus-cn-hangzhou-s-5df9f1f63c3f2876a5c3f846.oss-cn-hangzhou.aliyuncs.com/1649763566036270.mp4');

-- --------------------------------------------------------

--
-- Table structure for table `word_class`
--

CREATE TABLE `word_class` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `word_class`
--

INSERT INTO `word_class` (`id`, `name`) VALUES
(1, 'Greetings'),
(2, 'General conversation'),
(3, 'Numbers'),
(4, 'Time'),
(5, 'Date'),
(6, 'Weather'),
(7, 'Direction and places'),
(8, 'Hobbies'),
(9, 'Transportation'),
(10, 'Colors'),
(11, 'Studies'),
(12, 'Animals'),
(13, 'Family'),
(14, 'Currency');

-- --------------------------------------------------------

--
-- Table structure for table `word_list`
--

CREATE TABLE `word_list` (
  `id` int(11) NOT NULL,
  `english_text` varchar(255) NOT NULL,
  `chinese_char` varchar(255) NOT NULL,
  `pinyin` varchar(255) NOT NULL,
  `class_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `word_list`
--

INSERT INTO `word_list` (`id`, `english_text`, `chinese_char`, `pinyin`, `class_id`) VALUES
(1, 'hello', '你好', 'Nǐ hǎo', 1),
(2, 'nice to meet you', '很高兴见到你', 'hěn gāoxìng jiàn dào nǐ', 1),
(3, 'nice to meet you, too', '我也很高兴见到你', 'wǒ yě hěn gāoxìng jiàn dào nǐ', 1),
(4, 'what is your name?', '你叫什么名字？', 'nǐ jiào shénme míngzì?', 1),
(5, 'my name is ...', '我的名字是 ...', 'Wǒ de míngzì shì...', 1),
(6, 'how are you?', '你好吗？', 'Nǐ hǎo ma?', 1),
(7, 'i\'m fine', '我很好', 'Wǒ hěn hǎo', 1),
(8, 'i\'m not fine', '我不好', 'wǒ bù hǎo', 1),
(9, 'and you?', '你呢？', ' nǐ ne?', 1),
(10, 'how old are you?', '你几岁？', 'Nǐ jǐ suì?', 1),
(11, 'i\'m ... years old', '我……岁', 'Wǒ……suì', 1),
(12, 'where are you from?', '你从哪里来？', 'nǐ cóng nǎlǐ lái?', 1),
(13, 'i\'m from...', '我来自...', 'Wǒ láizì...', 1),
(14, 'where are you going?', '你要去哪里？', 'Nǐ yào qù nǎlǐ?', 1),
(15, 'goodbye', '再见', 'Zàijiàn', 1),
(16, 'see you later', '回头见', 'huítóu jiàn', 1),
(17, 'see you tomorrow', '明天见', 'míngtiān jiàn', 1),
(18, 'can you speak english?', '你能说英语吗？', 'nǐ néng shuō yīngyǔ ma?', 1),
(19, 'i can\'t speak Chinese', '我不会说中文', 'Wǒ bù huì shuō zhōngwén', 1),
(20, 'good morning', '早上好', 'zǎoshang hǎo', 1),
(21, 'good afternoon', '下午好', 'Xiàwǔ hǎo', 1),
(22, 'good evening', '晚上好\n', 'wǎnshàng hǎo', 1),
(23, 'good night', '晚安', 'wǎn\'ān', 1),
(24, 'happy birthday!', '生日快乐！', 'shēngrì kuàilè!', 1),
(25, 'happy new year!', '新年快乐！', 'Xīnnián kuàilè!\n', 1),
(26, 'yes', '是的', 'Shì de', 2),
(27, 'no', '不', 'bù', 2),
(28, 'do you understand?', '你明白吗？', 'nǐ míngbái ma?', 2),
(29, 'i don\'t understand', '我不明白', 'Wǒ bù míngbái', 2),
(30, 'i understand', '我明白', 'wǒ míngbái', 2),
(31, 'thank you', '谢谢你', 'xièxiè nǐ', 2),
(32, 'i\'m sorry', '对不起\n', 'duìbùqǐ', 2),
(33, 'can you speak slowly?', '你可以讲慢点吗？', 'nǐ kěyǐ jiǎng màn diǎn ma?\n', 2),
(34, 'just a moment', '一会儿', 'Yīhuǐ\'er', 2),
(35, 'it\'s alright', '没关系\n', 'méiguānxì', 2),
(36, 'i don\'t like it', '我不喜欢', 'wǒ bù xǐhuān', 2),
(37, 'i like it', '我喜欢', 'wǒ xǐhuān', 2),
(38, 'you\'re welcome', '别客气', 'bié kèqì\n', 2),
(39, 'i want...', '我想要...', 'wǒ xiǎng yào...', 2),
(40, 'can you speak english?', '你能说英语吗？', 'Nǐ néng shuō yīngyǔ ma?\n', 2),
(41, 'what does this mean?', '这是什么意思？\n', 'Zhè shì shénme yìsi?', 2),
(42, 'no problem', '没问题', 'Méi wèntí', 2),
(43, 'how much?', '多少？', 'duōshǎo?', 2),
(44, 'why?', '为什么？', 'Wèishéme?', 2),
(45, 'which?', '哪一个？', 'Nǎ yīgè?', 2),
(46, 'where?', '在哪里？\n', 'Zài nǎlǐ?\n', 2),
(47, 'when?', '什么时候？\n', 'Shénme shíhòu?\n', 2),
(48, 'what are you doing now?', '你在做什么？\n', 'Nǐ zài zuò shénme?\n', 2),
(49, '0', '零\n', 'Líng\n', 3),
(50, '1', '一\n', 'yī\n ', 3),
(51, '2', '二\n', 'èr\n', 3),
(52, '3', '三\n', 'sān\n', 3),
(53, '4', '四\n', 'sì\n', 3),
(54, '5', '五\n', 'wǔ\n', 3),
(55, '6', '六\n', 'liù\n', 3),
(56, '7', '七\n', 'qī\n', 3),
(57, '8', '八\n', 'bā\n', 3),
(58, '9', '九\n', 'jiǔ\n', 3),
(59, '10', '十\n', 'shí', 3),
(60, '11', '', '', 3),
(61, '12', '', '', 3),
(62, '13', '', '', 3),
(63, '14', '', '', 3),
(64, '15', '', '', 3),
(65, '16', '', '', 3),
(66, '17', '', '', 3),
(67, '18', '', '', 3),
(68, '19', '', '', 3),
(69, '20', '', '', 3),
(70, '30', '', '', 3),
(71, '40', '', '', 3),
(72, '50', '', '', 3),
(73, '60', '', '', 3),
(74, '70', '', '', 3),
(75, '80', '', '', 3),
(76, '90', '', '', 3),
(77, '100', '', '', 3),
(78, 'thousand', '千\n', 'Qiān\n', 3),
(79, 'ten thousand', '万\n', 'wàn\n', 3),
(80, 'hundred thousand', '成百上千的\n', 'chéng bǎi shàng qiān de\n', 3),
(81, 'million', '百万\n', 'bǎi wàn\n', 3),
(82, 'billion', '十亿\n', 'shí yì\n', 3),
(83, 'first', '第一的\n', 'dì yī de\n', 3),
(84, 'second', '第二\n', 'dì èr\n', 3),
(85, 'third', '第三\n', 'dì sān', 3),
(86, 'what time is it?', '现在是几奌？\n', 'xiànzài shì jǐ diǎn?\n', 4),
(87, 'morning', '早上', 'Zǎoshang\n', 4),
(88, 'noon', '中午\n', 'zhōngwǔ\n', 4),
(89, 'afternoon', '下午\n', 'xiàwǔ\n', 4),
(90, 'evening', '晚上\n', 'wǎnshàng\n', 4),
(91, 'night', '夜晚\n', 'yèwǎn\n', 4),
(92, 'second', '秒', 'miǎo\n', 4),
(93, 'minute', '分钟\n', 'fēnzhōng\n', 4),
(94, 'hour', '小时\n', 'xiǎoshí\n', 4),
(95, 'day', '天\n', 'tiān\n', 4),
(96, 'week', '星期\n', 'xīngqí\n', 4),
(97, 'month', '月\n', 'yuè\n', 4),
(98, 'year', '年\n', 'nián\n', 4),
(99, 'now', '现在\n', 'Xiànzài\n', 4),
(100, 'past', '过去的\n', 'guòqù de\n', 4),
(101, 'future', '未来\n', 'wèilái\n', 4),
(102, 'today', '今天\n', 'jīntiān\n', 4),
(103, 'tomorrow', '明天\n', 'míngtiān\n', 4),
(104, 'yesterday', '昨天\n', 'zuótiān\n', 4),
(105, 'last week', '上个星期\n', 'shàng gè xīngqí\n', 4),
(106, 'last month', '上个月\n', 'shàng gè yuè\n', 4),
(107, 'next week', '下周\n', 'xià zhōu\n', 4),
(108, 'next month', '下个月\n', 'xià gè yuè\n', 4),
(109, 'next year', '明年\n', 'míngnián\n', 4),
(110, 'monday', '周一\n', 'zhōuyī\n', 5),
(111, 'tuesday', '周二\n', 'zhōu\'èr\n', 5),
(112, 'wednesday', '周三\n', 'zhōusān\n', 5),
(113, 'thursday', '周四\n', 'zhōu sì\n', 5),
(114, 'friday', '星期五\n', 'xīngqíwǔ\n', 5),
(115, 'saturday', '周六', 'zhōu liù\n', 5),
(116, 'sunday', '星期日', 'xīngqírì', 5),
(117, 'january', '一月\n', 'Yī yuè\n', 5),
(118, 'february', '二月\n', 'èr yuè\n', 5),
(119, 'march', '三月', 'san yue', 5),
(120, 'april', '四月\n', 'sì yuè\n', 5),
(121, 'may', '五月', 'wu yue', 5),
(122, 'june', '六月\n', 'liù yuè\n', 5),
(123, 'july', '七月\n', 'qī yuè\n', 5),
(124, 'august', '八月\n', 'bā yuè\n', 5),
(125, 'september', '九月\n', 'jiǔ yuè\n', 5),
(126, 'november', '十一月\n', 'shíyī yuè\n', 5),
(127, 'december', '十二月\n', 'shí\'èr yuè\n', 5),
(128, 'spring', '春天\n', 'chūntiān\n', 5),
(129, 'summer', '夏天\n', 'xiàtiān\n', 5),
(130, 'autumn', '秋天\n', 'qiūtiān\n', 5),
(131, 'winter', '冬天\n', 'dōngtiān\n', 5),
(132, 'What is the weather like?', '天气怎么样', 'Tian qi zen me yang', 6),
(133, 'it\'s hot', '很热', 'Hěn rè', 6),
(134, 'it\'s warm', '很温暖', 'hěn wēnnuǎn', 6),
(135, 'it\'s cold', '很冷', 'hen leng', 6),
(136, 'it\'s foggy', '雾蒙蒙的\n', 'wù méngméng de\n', 6),
(137, 'it\'s lovely', '太好了', 'tai hao le', 6),
(138, 'it\'s dry', '干了\n', 'gànle\n', 6),
(139, 'it\'s humid', '', '', 6),
(140, 'it\'s cloudy', '今天多云', 'jīntiān duōyún', 6),
(141, 'it\'s rainy', '常在下雨', 'cháng zàixià yǔ\n', 6),
(142, 'it\'s snowy', '下雪了', 'xià xuěle', 6),
(143, 'it\'s comfortable', '很舒服\n', 'hěn shūfú\n', 6),
(144, 'Where is...?', '哪里...？\n', 'nǎlǐ...?\n', 7),
(145, 'I\'m looking for...', '我在找...\n', 'Wǒ zài zhǎo...\n', 7),
(146, 'left', '左', 'zuǒ ', 7),
(147, 'right', '右', 'yòu', 7),
(148, 'up', '向上', 'xiàngshàng', 7),
(149, 'down', '向下', 'xiàng xià', 7),
(150, 'turn left', '左转', 'zuǒ zhuǎn', 7),
(151, 'turn right', '右转', 'yòu zhuǎn', 7),
(152, 'go straight', '直行', 'zhíxíng', 7),
(153, 'train station', '火车站\n', 'huǒchēzhàn\n', 7),
(154, 'airport', '飞机场\n', 'fēijī chǎng\n', 7),
(155, 'bus stop', '巴士车站\n', 'bāshì chē zhàn\n', 7),
(156, 'taxi stand', '的士站\n', 'dí shì zhàn\n', 7),
(157, 'subway', '地铁\n', 'dìtiě\n', 7),
(158, 'bank', '银行\n', 'yínháng\n', 7),
(159, 'hotel', '酒店\n', 'jiǔdiàn\n', 7),
(160, 'supermarket', '超级市场\n', 'chāojí shìchǎng\n', 7),
(161, 'pharmacy', '药店\n', 'yàodiàn\n', 7),
(162, 'school', '学校\n', 'xuéxiào\n', 7),
(163, 'house', '家', 'jia', 7),
(164, 'what do you like to do?', '你喜欢做什么？\n', 'Nǐ xǐhuān zuò shénme?\n', 8),
(165, 'which sports do you like best?', '你最喜欢哪种运动？\n', 'Nǐ zuì xǐhuān nǎ zhǒng yùndòng?\n', 8),
(166, 'which sport do you play?', '你玩什么运动？\n', 'Nǐ wán shénme yùndòng?\n', 8),
(167, 'i like...', '我喜欢...\n', 'Wǒ xǐhuān...\n', 8),
(168, 'i don\'t  like...', '我不喜欢...\n', 'Wǒ bù xǐhuān...\n', 8),
(169, 'cooking', '烹饪\n', 'Pēngrèn\n', 8),
(170, 'dancing', '跳舞\n', 'tiàowǔ\n', 8),
(171, 'reading', '阅读\n', 'yuèdú\n', 8),
(172, 'singing', '唱歌\n', 'chànggē\n', 8),
(173, 'sleeping', '睡眠\n', 'shuìmián\n', 8),
(174, 'traveling', '旅行\n', 'lǚxíng\n', 8),
(175, 'badminton', '羽毛球\n', 'yǔmáoqiú\n', 8),
(176, 'basketball', '篮球\n', 'lánqiú\n', 8),
(177, 'football', '足球\n', 'zúqiú\n', 8),
(178, 'tennis', '网球\n', 'wǎngqiú\n', 8),
(179, 'volleyball', '排球\n', 'páiqiú\n', 8),
(180, 'music', '音乐\n', 'yīnyuè\n', 8),
(181, 'Where can i buy a ticket?', '我在哪里可以买到票？\n', 'Wǒ zài nǎlǐ kěyǐ mǎi dào piào?\n', 9),
(182, 'can you help me?', '你能帮助我吗？\n', 'Nǐ néng bāngzhù wǒ ma?\n', 9),
(183, 'I\'m lost', '我迷路了\n', 'Wǒ mílùle\n', 9),
(184, 'please put the meter on', '请打开仪表\n', 'qǐng dǎkāi yíbiǎo\n', 9),
(185, 'please stop here', '请停在这里\n', 'qǐng tíng zài zhèlǐ\n', 9),
(186, 'please take me to this address', '请带我到这个地址\n', 'qǐng dài wǒ dào zhège dìzhǐ\n', 9),
(187, 'luggage', '行李\n', 'xínglǐ\n', 9),
(188, 'at what station should i change?', '我应该在哪个站换车？\n', 'wǒ yīnggāi zài nǎge zhàn huàn chē?\n', 9),
(189, 'What color is it?', '它是什么颜色？\n', 'Tā shì shénme yánsè?\n', 10),
(190, 'white', '白色的\n', 'Báisè de\n', 10),
(191, 'black', '黑色的\n', 'hēisè de\n', 10),
(192, 'blue', '蓝色的\n', 'lán sè de\n', 10),
(193, 'red', '红色的\n', 'hóngsè de\n', 10),
(194, 'yellow', '黄色的\n', 'huángsè de\n', 10),
(195, 'pink', '粉色的\n', 'fěnsè de\n', 10),
(196, 'gold', '金子\n', 'jīnzi\n', 10),
(197, 'orange', '橙\n', 'chéng\n', 10),
(198, 'purple', '紫色的\n', 'zǐsè de\n', 10),
(199, 'brown', '棕色的\n', 'zōngsè de\n', 10),
(200, 'grey', '灰色的\n', 'huīsè de\n', 10),
(201, 'what is your favorite color?', '什么是你最喜欢的颜色？\n', 'shénme shì nǐ zuì xǐhuān de yánsè?\n', 10),
(202, 'what are you studying?', '你在学什么？\n', 'Nǐ zàixué shénme?\n', 11),
(203, 'i\'m studying..', '我在学习。。\n', 'Wǒ zài xuéxí..\n', 11),
(204, 'I\'m working', '我在工作\n', 'Wǒ zài gōngzuò\n', 11),
(205, 'chinese language', '中文\n', 'zhōngwén\n', 11),
(206, 'foreign language', '外语\n', 'wàiyǔ\n', 11),
(207, 'art', '艺术\n', 'yìshù\n', 11),
(208, 'architecture', '建筑学\n', 'jiànzhú xué\n', 11),
(209, 'fiinance', '财务\n', 'cáiwù\n', 11),
(210, 'computer science', '计算机科学\n', 'jìsuànjī kēxué\n', 11),
(211, 'economics', '经济学\n', 'jīngjì xué\n', 11),
(212, 'literature', '文学\n', 'wénxué\n', 11),
(213, 'agriculture', '农业\n', 'nóngyè\n', 11),
(214, 'education', '教育\n', 'jiàoyù\n', 11),
(215, 'psychology', '心理学\n', 'xīnlǐ xué\n', 11),
(216, 'what is your native language?', '什么是你的母语？\n', 'shénme shì nǐ de mǔyǔ?\n', 11),
(217, 'what languages do you speak', '你说什么语言\n', 'Nǐ shuō shénme yǔyán\n', 11),
(218, 'chinese', '汉语\n', 'zhōngwén\n', 11),
(219, 'english', '英语\n', 'yīngyǔ\n', 11),
(220, 'french', '法语\n', 'fǎyǔ\n', 11),
(221, 'dutch', '荷兰语\n', 'hélán yǔ\n', 11),
(222, 'japanese', '日语\n', 'rìyǔ\n', 11),
(223, 'cat ', '猫\n', 'māo\n', 12),
(224, 'dog', '狗\n', 'gǒu\n', 12),
(225, 'wolf', '狼\n', 'Láng\n', 12),
(226, 'fox', '狐狸\n', 'húlí\n', 12),
(227, 'giraffe', '长颈鹿\n', 'chángjǐnglù\n', 12),
(228, 'squirrel', '松鼠\n', 'sōngshǔ\n', 12),
(229, 'deer', '鹿\n', 'lù\n', 12),
(230, 'tiger', '老虎\n', 'lǎohǔ\n', 12),
(231, 'monkey', '猴\n', 'hóu\n', 12),
(232, 'gorilla', '大猩猩\n', 'dà xīngxīng\n', 12),
(233, 'baboon', '狒狒\n', 'fèifèi\n', 12),
(234, 'bat', '蝙蝠\n', 'biānfú\n', 12),
(235, 'snake', '蛇\n', 'shé\n', 12),
(236, 'how many members are there in your family?', '你家有几个成员？\n', 'nǐ jiā yǒu jǐ gè chéngyuán?\n', 13),
(237, 'do you have...?', '你有...？\n', 'Nǐ yǒu...?\n', 13),
(238, 'father', '父亲\n', 'Fùqīn\n', 13),
(239, 'mother', '母亲\n', 'mǔqīn\n', 13),
(240, 'parents', '父母\n', 'fùmǔ\n', 13),
(241, 'older brother', '哥哥\n', 'gēgē\n', 13),
(242, 'younger brother', '弟弟\n', 'dìdì\n', 13),
(243, 'older sister', '姐姐\n', 'jiějiě\n', 13),
(244, 'younger sister', '妹妹\n', 'mèimei\n', 13),
(245, 'spouse', '伴侣\n', 'bànlǚ\n', 13),
(246, 'son', '儿子\n', 'Érzi\n', 13),
(247, 'daughter', '女儿\n', 'nǚ\'ér\n', 13),
(248, 'uncle', '叔叔\n', 'shūshu\n', 13),
(249, 'aunt', '阿姨\n', 'āyí\n', 13),
(250, 'niece', '侄女\n', 'zhínǚ\n', 13),
(251, 'nephew', '侄子\n', 'zhízi\n', 13),
(252, 'cousin', '表哥\n', 'biǎo gē\n', 13),
(253, 'Chinese yuan', '人民币\n', 'rénmínbì\n', 14),
(254, 'united states dollar', '美国美元\n', 'měiguó měiyuán\n', 14),
(255, 'exchange', '交换\n', 'jiāohuàn\n', 14),
(256, 'exchange rate', '汇率\n', 'huìlǜ\n', 14),
(257, 'reciept', '收据\n', 'shōujù\n', 14),
(258, 'credit card', '信用卡\n', 'xìnyòngkǎ\n', 14),
(259, 'password', '密码\n', 'mìmǎ\n', 14),
(260, 'bank', '银行\n', 'yínháng\n', 14),
(261, 'service charge', '服务费\n', 'fúwù fèi\n', 14);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answers`
--
ALTER TABLE `answers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `q_id` (`q_id`);

--
-- Indexes for table `audio_answer`
--
ALTER TABLE `audio_answer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `audio_level`
--
ALTER TABLE `audio_level`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `audio_question`
--
ALTER TABLE `audio_question`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `culture_details`
--
ALTER TABLE `culture_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `culture_table`
--
ALTER TABLE `culture_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `culture_type`
--
ALTER TABLE `culture_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dialogue`
--
ALTER TABLE `dialogue`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `finals`
--
ALTER TABLE `finals`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `initials`
--
ALTER TABLE `initials`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pinyin_class`
--
ALTER TABLE `pinyin_class`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `quiz_level`
--
ALTER TABLE `quiz_level`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `videos`
--
ALTER TABLE `videos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `word_class`
--
ALTER TABLE `word_class`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `word_list`
--
ALTER TABLE `word_list`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answers`
--
ALTER TABLE `answers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `audio_answer`
--
ALTER TABLE `audio_answer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `audio_level`
--
ALTER TABLE `audio_level`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `audio_question`
--
ALTER TABLE `audio_question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `culture_details`
--
ALTER TABLE `culture_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `culture_table`
--
ALTER TABLE `culture_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `culture_type`
--
ALTER TABLE `culture_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `dialogue`
--
ALTER TABLE `dialogue`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `finals`
--
ALTER TABLE `finals`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `initials`
--
ALTER TABLE `initials`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `pinyin_class`
--
ALTER TABLE `pinyin_class`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `quiz_level`
--
ALTER TABLE `quiz_level`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `videos`
--
ALTER TABLE `videos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `word_class`
--
ALTER TABLE `word_class`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `word_list`
--
ALTER TABLE `word_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=262;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
