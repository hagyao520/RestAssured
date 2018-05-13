package com.sales.webapi.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 只支持读取 .xlsx 所有方法读取数据时, 未对 Excel 格式类型进行判断处理 如 Excel 中有特殊数据类型, 需在 Excel
 * 中标注为文本类型, 程序才能正常处理
 * 
 * @author Roger
 * @version 1.0
 */
public class ExcelUtil {
    private static Logger logger = Logger.getLogger(ExcelUtil.class);

    private String filePath = null;
    private String sheetName = null;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 判断成员变量 filePath 是否为空
     * 
     * @return
     */
    private boolean isFilePathEmpty() {
        boolean flag = false;
        if ((null != filePath && (!filePath.equals("")))) {
            flag = true;
        }
        return flag;
    }

    private boolean isSheetNameEmpty() {
        boolean flag = false;
        if ((null != sheetName && (!sheetName.equals("")))) {
            flag = true;
        }
        return flag;
    }

    // 使用静态内部类创建外部类对象
    private static class Excel {
        private static ExcelUtil excelUtil = new ExcelUtil();
    }

    private ExcelUtil() {
    }

    // 获取 ExcelUtil 实例
    public static ExcelUtil getInstance() {
        return Excel.excelUtil;
    }

    /**
     * 检查传入的文件后缀是否为 xlsx
     * 
     * @param filePacht
     * @return
     */
    public boolean checkXlsx(File file) {
        boolean flag = false;

        if (file.exists()) {
            String str = file.getName();
            if (str.substring(str.lastIndexOf(".") + 1).equals("xlsx"))
                flag = true;
        } else {
            logger.info(file.getName() + ", 文件不存在...");
            System.out.println(file.getName() + ", 文件不存在...");
        }

        return flag;
    }

    /**
     * 
     * @param file
     * @return true: 文件未被操作; false: 文件正在被操作.
     */
    public boolean isFile(File file) {
        return file.renameTo(file);
    }

    public File createFile() {
        File file = null;
        if (isFilePathEmpty()) {
            file = new File(filePath);
        } else {
            logger.error("filePath 不能为: "
                    + filePath
                    + ", 请先使用 'ExcelUtil.getInstance().setFilePath(filePath)' 设置!");
             System.out.println("filePath 不能为: " + filePath +", 请先使用 'ExcelUtil.getInstance().setFilePath(filePath)' 设置!");
            System.exit(-1);
        }
        return file;
    }

    /**
     * 创建 XSSFWorkbook
     * 
     * @param 文件路径
     * @return
     */
    public XSSFWorkbook createExcelWorkBook() {
        File file = createFile();

        BufferedInputStream in = null;
        XSSFWorkbook book = null;

        if (checkXlsx(file)) {
            try {
                in = new BufferedInputStream(new FileInputStream(file));
                book = new XSSFWorkbook(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return book;
    }

    /**
     * 创建 XSSFSheet
     * 
     * @param sheetName
     * @return
     */
    public XSSFSheet createExcelSheet(String sheetName) {
        XSSFSheet sheet = null;
        if (isSheetNameEmpty()) {
            XSSFWorkbook book = createExcelWorkBook();
            if (book != null) {
                int sheetCount = book.getNumberOfSheets();
                for (int i = 0; i < sheetCount; i++) {
                    if (sheetName.equals(book.getSheetName(i))) {
                        sheet = book.getSheet(sheetName);
                        break;
                    }
                }
            }
        } else {
            logger.error("sheetName 不能为: "
                    + sheetName
                    + ", 请先使用 'ExcelUtil.getInstance().setSheetName(SheetName)' 设置!");
             System.out.println("sheetName 不能为: " + sheetName + ", 请先使用 'ExcelUtil.getInstance().setSheetName(SheetName)' 设置!");
            System.exit(-1);
        }

        return sheet;
    }

    /**
     * 获取指定行
     * 
     * @param sheetName
     * @param line
     *            行索引, 从 0 开始
     * @return
     */
    public XSSFRow getExcelRow(int line) {
        XSSFRow row = null;
        if (line <= getSheetMaxRow())
            row = createExcelSheet(sheetName).getRow(line);
        return row;
    }

    /**
     * 获取指定 sheet 中最大行数
     * 
     * @param sheetName
     * @return
     */
    public int getSheetMaxRow() {
        int maxRow = -1;
        if (createExcelSheet(sheetName) != null)
            maxRow = createExcelSheet(sheetName).getLastRowNum();
        return maxRow;
    }

    /**
     * 使用正则表达式去掉多余的.与0
     * 
     * @param s
     * @return
     */
    private String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            // 去掉多余的 0
            s = s.replaceAll("0+?$", "");
            // 如果最后一位是.则去掉
            s = s.replaceAll("[.]$", "");
        }
        return s;
    }

    /**
     * 获取 Excel 指定行数据
     * 
     * @param sheetName
     * @param line
     * @return 返回一个一维数组
     */
    public String[] readExcelRows(int line) {
        String[] result = null;
        XSSFRow row = getExcelRow(line);
        int maxRow = getSheetMaxRow();
        if (row != null && maxRow > -1) {
            int columnNum = row.getLastCellNum();
            result = new String[columnNum];
            for (int i = 0; i < columnNum; i++) {
                // 判断单元格是否为空, 不进行判断时, 如遇到空白单元格时, 抛出空指针异常
                if (null != row.getCell(i))
                    result[i] = subZeroAndDot(row.getCell(i).toString().trim());
            }
        }
        return result;
    }

    /**
     * 获取指定单元格中内容
     * 
     * @param sheetName
     * @param line
     *            行
     * @param column
     *            列
     * @return
     */
    public String readExcelCell(int line, int column) {
        String[] value = null;
        String result = "";
        value = readExcelRows(line);
        if (value != null) {
            result = value[column];
        }
        return result;
    }

    /**
     * 从指定行开始读取 Excel 中所有数据
     * 
     * @param sheetName
     *            sheet 名称
     * @param line
     *            表标题所在行
     * @return 返回一个包含 HashMap<String, String> 的 ArrayList; 格式：[{第一行数据},{第二行数据}]
     *         {列 1 标题 = 列 1 值, 列 2 标题 = 列 2 值}
     */
    public List<Map<String, String>> readExcelAllData(int line) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        // 读取标题行
        String[] titleRow = readExcelRows(line);
        int maxRow = getSheetMaxRow();
        XSSFRow row;
        if (null != titleRow && maxRow != -1) {
            // i = line + 1; 标题的下一行开始取数据
            for (int i = line + 1; i <= maxRow; i++) {
                row = getExcelRow(i);
                Map<String, String> map = new HashMap<String, String>();
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    if (null != row.getCell(j)) {
                        String value = subZeroAndDot(row.getCell(j).toString()
                                .trim());
                        map.put(titleRow[j], value);
                    }
                }
                result.add(map);
            }
        }
        return result;
    }

    /**
     * 从指定数据行开始读取 Excel 所有数据
     * 
     * @param sheetName
     *            sheet 名称
     * @param line
     *            数据读取开始行
     * @return
     */
    public String[][] readDataArrayAll(int line) {
        ArrayList<Object> list = new ArrayList<Object>();
        int maxRow = getSheetMaxRow();
        XSSFRow row = null;
        int columnNum = 0;
        if (maxRow != -1) {
            for (int i = line; i <= maxRow; i++) {
                row = getExcelRow(i);
                columnNum = row.getLastCellNum();
                String[] values = new String[columnNum];

                for (int j = 0; j < columnNum; j++) {
                    if (null != row.getCell(j)) {
                        String tempStr = subZeroAndDot(row.getCell(j)
                                .toString().trim());
                        values[j] = tempStr;
                    }
                }
                list.add(values);
            }
        }

        String[][] result = new String[list.size()][columnNum];

        for (int i = 0; i < result.length; i++) {
            result[i] = (String[]) list.get(i);
        }
        return result;
    }

    /**
     * 从指定数据行开始读取 Excel 所有数据
     * 
     * @param sheetName
     *            sheet 名称
     * @param line
     *            数据读取开始行
     * @return 返回一个包含 list 的 list; 格式 [[第一行数据],[第二行数据]]
     */
    public List<List<String>> readDataListAll(int line) {
        List<List<String>> result = new ArrayList<List<String>>();

        int maxRow = getSheetMaxRow();
        XSSFRow row = null;

        if (maxRow != -1) {
            for (int i = line; i <= maxRow; i++) {
                row = getExcelRow(i);
                List<String> list = new ArrayList<String>();
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    if (null != row.getCell(j)) {
                        String value = subZeroAndDot(row.getCell(j).toString()
                                .trim());
                        list.add(value);
                    }
                }
                result.add(list);
            }
        }
        return result;
    }

    /**
     * 将数据写入指定单元格
     * 
     * @param sheetName
     *            sheet 名称
     * @param line
     *            需写入的行
     * @param column
     *            需写入的列
     * @param content
     *            需写入的内容
     * @throws IOException
     */
    public void writeExcelCell(int line, int column, String content)
            throws IOException {
        XSSFWorkbook book = createExcelWorkBook();
        XSSFSheet sheet = book.getSheet(sheetName);

        if (line <= sheet.getLastRowNum() && line >= 0) {
            XSSFRow row = sheet.getRow(line);

            if (column < row.getLastCellNum() && column >= 0) {
                if (isFile(createFile())) {
                    FileOutputStream out = new FileOutputStream(createFile());
                    XSSFCell cell = row.getCell(column);
                    if (null == cell) {
                        cell = row.createCell(column);
                    }
                    cell.setCellValue(content);
                    book.write(out);
                    out.close();
                } else {
                    logger.error("文件: " + filePath + ", 正被使用, 请关闭! 程序执行终止...");
                    System.out.println("文件: " + filePath + ", 正被使用, 请关闭! 程序执行终止...");
                    System.exit(-1);
                }
            } else
                logger.error("列索引越界...");
            // System.out.println("列索引越界...");
        } else
            logger.error("行索引越界...");
        // System.out.println("行索引越界...");
    }
    
    /**
     * 从指定行的下一行开始读取某列的所有数据
     * @param titleLineIndex  指定行读取
     * @param columnName         列名
     * @return
     */
    public String[] readExcelColumnData(int titleLineIndex, String columnName) {
        String[] result = null;
        int columnIndex = getColumnIndex(titleLineIndex, columnName);
        int maxRow = getSheetMaxRow();

        if (columnIndex != -1 && maxRow != -1) {
            result = new String[maxRow - titleLineIndex];
            
            for (int i = 0; i < maxRow - titleLineIndex; i++) {
                result[i] = readExcelCell(titleLineIndex + 1 + i, columnIndex);
            }
        }
        return result;
    }
    

    /**
     * 获取列的索引
     * 
     * @param sheetName
     *            sheet 名称
     * @param line
     *            需要获取的行
     * @param columnName
     *            列名
     * @return
     */
    public int getColumnIndex(int line, String columnName) {
        int index = -1;
        String[] title = readExcelRows(line);

        if (null != title) {
            for (int i = 0; i < title.length; i++) {
                if (columnName.equals(title[i]))
                    index = i;
            }
        }
        return index;
    }

    /**
     * 设置单元格背景色
     * 
     * @param sheetName
     *            sheet 名称
     * @param titleRow
     *            列标题所在行号, 索引 0 开始
     * @param columnName
     *            需要获取列索引的列名称
     * @param line
     *            需要设置颜色单元格所在行
     * @param color
     *            需设置的颜色; 0: 红色; 1: 绿色; 2: 灰色; 3: 黄色; 4: 白色.
     * @throws IOException
     */
    public void setCellBackgroundColor(int titleRow, String columnName,
            int line, int color) throws IOException {
        XSSFWorkbook book = createExcelWorkBook();
        XSSFSheet sheet = book.getSheet(sheetName);

        int columnIndex = getColumnIndex(titleRow, columnName);
        XSSFRow row = sheet.getRow(line);
        XSSFCell cell = row.getCell(columnIndex);
        XSSFCellStyle old = cell.getCellStyle();
        XSSFCellStyle temp = book.createCellStyle();
        temp.cloneStyleFrom(old); // 拷贝旧的样式

        switch (color) {
        case 0:
            // 红色
            temp.setFillForegroundColor(IndexedColors.RED.getIndex());
            break;
        case 1:
            // 绿色
            temp.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
            break;
        case 2:
            // 灰色
            temp.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
            break;
        case 3:
            // 黄色
            temp.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            break;
        case 4:
            // 白色
            temp.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            break;
        default:
        	logger.error("设定颜色参数 (color) 错误...");
            System.out.println("设定颜色参数 (color) 错误...");
            break;
        }

        temp.setFillPattern(CellStyle.SOLID_FOREGROUND);
        // XSSFFont font = book.createFont();
        // font.setFontHeightInPoints((short)9); // 字体大小
        // font.setFontName("宋体");
        // temp.setFont(font);
        cell.setCellStyle(temp);
        if (isFile(createFile())) {
            FileOutputStream out = new FileOutputStream(createFile());
            book.write(out);
            out.close();
        } else {
            logger.error("文件: " + filePath + ", 正被使用, 请关闭! 程序执行终止...");
             System.out.println("文件: " + filePath + ", 正被使用, 请关闭! 程序执行终止...");
            System.exit(-1);
        }
    }

}