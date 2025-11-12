![Spring Boot + MyBatis]({{ site.baseurl }}/assets/gitbook/images/japangoods-spring-index.png "Spring Boot + MyBatis")
![Spring Boot + MyBatis]({{ site.baseurl }}/assets/gitbook/images/japangoods-spring-detail.png "Spring Boot + MyBatis")
```python
# 创建数据库
import pymysql
from pymysql import Error

def create_database_and_tables():
    connection = None
    try:
        # 连接MySQL服务器 - 请替换为您的实际用户名和密码
        connection = pymysql.connect(
            host='localhost',
            user='root',  # 替换为您的MySQL用户名
            password='******',  # 替换为您的MySQL密码
            charset='utf8mb4'
        )
        
        with connection.cursor() as cursor:
            print("成功连接到MySQL服务器")
            
            # 创建数据库
            print("正在创建数据库...")
            cursor.execute("CREATE DATABASE IF NOT EXISTS snack_learning CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
            cursor.execute("USE snack_learning")
            print("数据库 'snack_learning' 创建成功")
            
            # 创建零食表
            print("正在创建零食表...")
            create_table_query = """
            CREATE TABLE IF NOT EXISTS snacks (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100) NOT NULL COMMENT '零食名称',
                japanese_name VARCHAR(100) NOT NULL COMMENT '日语名称',
                english_name VARCHAR(100) NOT NULL COMMENT '英语名称',
                description TEXT COMMENT '零食描述',
                image_name VARCHAR(255) COMMENT '图片文件名',
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
            """
            cursor.execute(create_table_query)
            print("零食表创建成功")
            
            # 检查表中是否已有数据
            cursor.execute("SELECT COUNT(*) FROM snacks")
            count = cursor.fetchone()[0]
            
            if count == 0:
                # 插入示例数据
                print("正在插入示例数据...")
                insert_data_query = """
                INSERT INTO snacks (name, japanese_name, english_name, description, image_name) VALUES
                (%s, %s, %s, %s, %s)
                """
                
                snacks_data = [
                    ('巧克力', 'チョコレート', 'Chocolate', '美味的巧克力零食，口感丝滑', 'chocolate1001.jpg'),
                    ('糖果', 'キャンディ', 'Candy', '各种口味的水果糖，色彩缤纷', 'sugar1001.jpg'),
                    ('薯片', 'ポテトチップス', 'Potato Chips', '香脆可口的薯片，多种口味', 'potato-chips1001.jpg'),
                    ('杯面', 'カップ麺', 'Cup Noodles', '方便快捷的杯面，即冲即食', 'cup-noodles1001.jpg'),
                    ('草莓大福', 'いちご大福', 'Strawberry Daifuku', '软糯的草莓大福，甜而不腻', 'pudding1001.jpg'),
                    ('抹茶饼干', '抹茶クッキー', 'Matcha Cookies', '香浓抹茶味的饼干，茶香四溢', 'drink1001.jpg'),
                    ('哈密瓜面包', 'メロンパン', 'Melon Pan', '外表酥脆的哈密瓜面包，内里柔软', 'pudding1001.jpg'),
                    ('章鱼烧', 'たこ焼き', 'Takoyaki', '经典的章鱼小丸子，外脆内软', 'bath-bomb1001.jpg'),
                    ('饭团', 'おにぎり', 'Onigiri', '各种馅料的三角饭团，便携美味', 'yogurt1001.jpg'),
                    ('铜锣烧', 'どら焼き', 'Dorayaki', '红豆馅的铜锣烧，哆啦A梦最爱', 'chocolate1001.jpg'),
                    ('仙贝', 'せんべい', 'Senbei', '脆脆的米饼，传统日本零食', 'sugar1001.jpg'),
                    ('布丁', 'プリン', 'Pudding', '滑嫩的日式布丁，奶香浓郁', 'pudding1001.jpg'),
                    ('果冻', 'ゼリー', 'Jelly', '清爽的果冻，多种水果口味', 'drink1001.jpg'),
                    ('羊羹', 'ようかん', 'Yokan', '传统的日式甜点，红豆制成', 'rice-soup1001.jpg'),
                    ('团子', '団子', 'Dango', '软糯的团子串，多种口味', 'cookie1001.jpg')
                ]
                
                cursor.executemany(insert_data_query, snacks_data)
                connection.commit()
                print(f"成功插入 {len(snacks_data)} 条零食数据")
            else:
                print(f"表中已有 {count} 条数据，跳过插入")
            
            # 显示创建的表结构
            print("\n表结构：")
            cursor.execute("DESCRIBE snacks")
            for column in cursor:
                print(f"{column[0]:15} {column[1]:20} {column[2]}")
            
            # 显示部分数据
            print("\n示例数据：")
            cursor.execute("SELECT id, name, japanese_name, english_name FROM snacks LIMIT 5")
            for row in cursor:
                print(f"ID: {row[0]}, 名称: {row[1]}, 日语: {row[2]}, 英语: {row[3]}")
                
    except Error as e:
        print(f"数据库错误: {e}")
    finally:
        if connection:
            connection.close()
            print("\n数据库连接已关闭")

if __name__ == "__main__":
    create_database_and_tables()
```
```python
import pymysql

def quick_check():
    """快速检查数据库状态"""
    try:
        # 连接信息 - 请修改为您的实际信息
        conn = pymysql.connect(
            host='localhost',
            user='root',      # 您的用户名
            password='******', # 您的密码
            database='snack_learning'
        )
        
        cursor = conn.cursor()
        
        # 检查记录数量
        cursor.execute("SELECT COUNT(*) FROM snacks")
        count = cursor.fetchone()[0]
        
        print(f"✅ 数据库连接成功!")
        print(f"📊 零食表中有 {count} 条记录")
        
        # 显示前5条记录
        if count > 0:
            print("\n前5条记录:")
            cursor.execute("SELECT id, name, japanese_name, english_name FROM snacks LIMIT 5")
            for row in cursor:
                print(f"  {row[0]}. {row[1]} - {row[2]} - {row[3]}")
        
        cursor.close()
        conn.close()
        
    except Exception as e:
        print(f"❌ 检查失败: {e}")

if __name__ == "__main__":
    quick_check()
```
