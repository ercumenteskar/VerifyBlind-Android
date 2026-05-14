@echo off
chcp 65001 > nul

cd /d "%~dp0"

echo.
echo === VerifyBlind Android Release Trigger ===
echo.

:: PowerShell ile versionCode oku ve artir
for /f %%a in ('powershell -Command "(Get-Content app\version.properties | Where-Object {$_ -match 'versionCode'}).Split('=')[1].Trim()"') do set CURRENT=%%a
set /a NEW=%CURRENT%+1

:: version.properties'i guncelle (PS 5.1 uyumlu, BOM'suz)
powershell -Command "$c = (Get-Content 'app\version.properties' -Raw) -replace 'versionCode=%CURRENT%', 'versionCode=%NEW%'; [System.IO.File]::WriteAllText((Resolve-Path 'app\version.properties').Path, $c, [System.Text.UTF8Encoding]::new($false))"

echo versionCode: %CURRENT% -^> %NEW%
echo.

:: Sadece version.properties'i commit et
git add app\version.properties
git commit -m "release: bump versionCode to %NEW%"
if errorlevel 1 (
    echo HATA: git commit basarisiz.
    pause
    exit /b 1
)

git push
if errorlevel 1 (
    echo.
    echo HATA: git push basarisiz.
    echo Remote onunuzda olabilir. Terminalde 'git pull' yapip tekrar deneyin.
    git reset HEAD~1
    pause
    exit /b 1
)

echo.
echo GitHub Actions workflow tetikleniyor...
gh workflow run build-android.yml --ref main
if errorlevel 1 (
    echo HATA: workflow tetiklenemedi. 'gh auth login' yapmaniz gerekebilir.
    pause
    exit /b 1
)

echo.
echo Bitti! Build durumunu izlemek icin:
echo gh run list --workflow=build-android.yml
echo.
pause