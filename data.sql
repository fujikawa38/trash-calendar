INSERT INTO wards (name) VALUES
('中央区'),
('花見川区'),
('稲毛区'),
('若葉区'),
('緑区'),
('美浜区');

INSERT INTO towns (ward_id, name) VALUES
(1, '青葉町'),
(2, '朝日ヶ丘1～5丁目'),
(3, '穴川町'),
(4, '愛生町'),
(5, 'あすみが丘1～3丁目'),
(6, '磯辺1～5丁目');

INSERT INTO trash_types (name) VALUES
('可燃ごみ'),
('資源物（びん・缶・ペットボトル）'),
('資源物（古紙・布類）'),
('資源物（木の枝・刈り草・葉）'),
('不燃ごみ'),
('有害ごみ'),
('粗大ごみ'),
('その他');

INSERT INTO trash_schedules (town_id, trash_type_id, week_of_month, collection_day) VALUES
(1, 1, 'Every', 'Monday'),
(2, 5, '1', 'Friday'),
(3, 3, 'Every', 'Thursday'),
(4, 5, '2', 'Tuesday'),
(5, 1, 'Every', 'Saturday'),
(6, 5, '3', 'Tuesday');

INSERT INTO items (name, trash_type_id, trash_type_id2) VALUES
('アルミ缶（飲食用）', 2, NULL),
('延長コード・テーブルタップ', 5, 8),
('折りたたみ傘', 5, NULL),
('枝（太さ10cm以内、長さ50cm以内）', 4, 1),
('色鉛筆', 1, NULL);