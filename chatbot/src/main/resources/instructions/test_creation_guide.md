# 📘 **HƯỚNG DẪN TẠO BÀI THI (TEST CREATION GUIDE)**

Trang tạo bài thi gồm 4 phần quan trọng để người dùng tải dữ liệu (file
Excel, audio, hình ảnh). Vui lòng đọc kỹ hướng dẫn dưới đây để tạo bài
thi đúng chuẩn hệ thống.

------------------------------------------------------------------------

## 📝 1. Upload file Excel chứa câu hỏi của bài thi

-   Nhấn nút **Tải file Excel** để chọn file chứa toàn bộ câu hỏi.
-   File Excel phải bao gồm:
    -   Danh sách câu hỏi
    -   Đáp án
    -   Giải thích (nếu có)
    -   Mapping audio / hình ảnh nếu cần

**File Excel là bắt buộc** để hệ thống tạo bài thi.

------------------------------------------------------------------------

## 🎧 2. Upload audio cho *toàn bài thi* (nếu có)

**Tiêu đề khu vực:**\
\### *"File âm thanh cho toàn đề thi (nếu có)"*

-   Chỉ upload **1 file audio** dùng chung cho toàn bộ bài thi.
-   Áp dụng cho một số đề thi có 1 audio duy nhất.

------------------------------------------------------------------------

## 🎧 3. Upload audio cho *từng phần hoặc từng câu hỏi* (nếu có)

**Tiêu đề khu vực:**\
\### *"File âm thanh theo từng phần hoặc câu hỏi (nếu có)"*

Phần này dùng khi audio được chia nhỏ theo:

-   **Part**
-   **Section**
-   **Record** (đoạn hội thoại)

### ✔ Quy tắc đặt tên file audio

Format:

    TênBàiThi-TênPhần

### ⚠ Quy định quan trọng:

1.  **Không có dấu cách ở dấu "-"**\
    Ví dụ đúng:
    -   `ETS2024Test1-Part 1`\
    -   `IELTS2023Test2-Section 3`\
        Không đúng:\
    -   `ETS 2024 Test 1 - Part 1`
2.  **Tên phần phải chứa 1 trong 3 từ:**

-   `Part`
-   `Section`
-   `Record`

Ví dụ đúng:\
- `ETS2024Test1-Part3`\
- `IELTSMockTest-Section4`\
- `TOEICReviewSet-Record1`

------------------------------------------------------------------------

## 🖼 4. Upload hình ảnh cho từng câu hỏi (nếu có)

Một số câu hỏi cần hình ảnh (ví dụ: Part 1 TOEIC).\
Tên file ảnh phải đúng format:

    Tên đề thi-Phần câu hỏi-QuestionSốCâu

### ✔ Ví dụ chuẩn:

#### Ảnh cho một câu hỏi:

    ETS 2024 Test 1-Part 1-Question 1

#### Ảnh cho nhiều câu (62 → 64):

    ETS 2024 Test 1-Part 3-Question 62_64

### Lưu ý:

-   Giữ nguyên khoảng trắng trong tên đề thi.
-   Không dùng dấu tiếng Việt trong tên file.
-   Không đổi format vì hệ thống map dựa trên tên file.

------------------------------------------------------------------------

# 📦 Tổng kết quy tắc đặt tên

  ----------------------------------------------------------------------------------------------------
  Tài nguyên                   Format                        Ví dụ
  ---------------------------- ----------------------------- -----------------------------------------
  Audio toàn bài               Tên tùy ý                     `test1-listening.mp3`

  Audio từng phần              `TênBàiThi-TênPhần`           `ETS2024Test1-Part3`

  Ảnh một câu                  `TênĐềThi-Part-QuestionX`     `ETS 2024 Test 1-Part 1-Question 1`

  Ảnh nhiều câu                `TênĐềThi-Part-QuestionX_Y`   `ETS 2024 Test 1-Part 3-Question 62_64`
  ----------------------------------------------------------------------------------------------------

------------------------------------------------------------------------

# 🎯 Gợi ý chuẩn bị file

-   Dùng cùng một tên bài thi: `ETS 2024 Test 1`, `IELTS 2023 Test 2`...
-   Không dùng ký tự đặc biệt (#, %, \$, !...)
-   Không dùng tiếng Việt có dấu.
-   Kiểm tra kỹ trước khi upload để tránh lỗi mapping.
