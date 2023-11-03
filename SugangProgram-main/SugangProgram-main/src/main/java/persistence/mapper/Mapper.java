package persistence.mapper;

import org.apache.ibatis.annotations.*;
import persistence.dto.*;

import java.util.List;

public interface Mapper {

    @Insert("INSERT INTO accounts VALUES(#{id},#{password},#{user_type})")
    void insertAccounts(AccountsDTO dto);

    @Select("SELECT * FROM accounts")
    @Results(id = "AccountsResultSet", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "password", column = "password"),
            @Result(property = "user_type", column = "user_type")
    })
    List<AccountsDTO> selectAccountsAll( );

    @Select("SELECT * FROM accounts WHERE id=#{id}")
    @ResultMap("AccountsResultSet")
    List<AccountsDTO> selectAccountById(AccountsDTO dto);

    @Update("UPDATE accounts SET password=#{password} WHERE id=#{id}")
    void updateAccountsPW(AccountsDTO dto);

    @Delete("DELETE FROM accounts WHERE id=#{id}")
    void deleteAccounts(AccountsDTO dto);


    @Insert("INSERT INTO admins VALUE(#{admin_id})")
    void insertAdmins(AdminsDTO dto);

    @Select("SELECT * FROM admins")
    @Result(property = "admin_id", column = "admin_id")
    List<AdminsDTO> selectAdminsAll(AdminsDTO dto);

    @Delete("DELETE FROM admins WHERE admin_id=#{admin_id}")
    void deleteAdmins(AdminsDTO dto);


    @Insert("INSERT INTO applied_lectures VALUES(#{lecture_id},#{class_id},#{student_id})")
    void insertAL(AppliedLecturesDTO dto);

    @Select("SELECT * FROM applied_lectures")
    @Results(id="AppliedLecturesResultSet", value = {
            @Result(property = "lecture_id", column="lecture_id"),
            @Result(property = "class_id", column="class_id"),
            @Result(property = "student_id", column="student_id")
    })
    List<AppliedLecturesDTO> selectALAll(AppliedLecturesDTO dto);

    @Select("SELECT * FROM applied_lectures WHERE lecture_id=#{lecture_id} AND class_id=#{class_id}")
    @Result(property = "student_id", column = "student_id")
    List<AppliedLecturesDTO> selectALByLectureAndClassId(AppliedLecturesDTO dto);

    @Select("SELECT * FROM applied_lectures WHERE student_id=#{student_id}")
    @ResultMap("AppliedLecturesResultSet")
    List<AppliedLecturesDTO> selectALByStudentId(AppliedLecturesDTO dto);

    @Select("SELECT opened_lectures.* FROM opened_lectures,applied_lectures " +
            "WHERE opened_lectures.lecture_id=applied_lectures.lecture_id AND student_id=#{student_id}")
    @ResultMap("OpenedLecturesResultSet")
    List<OpenedLecturesDTO> selectALBySchedule(AppliedLecturesDTO dto);

    @Select("SELECT opened_lectures.schedule FROM opened_lectures,applied_lectures " +
            "WHERE student_id=#{student_id}")
    @ResultMap("OpenedLecturesResultSet")
    List<OpenedLecturesDTO> selectALScheduleByStudent(StudentsDTO dto);


    @Select("SELECT DISTINCT students.* from students,applied_lectures,opened_lectures " +
            "WHERE applied_lectures.lecture_id=opened_lectures.lecture_id " +
            "AND applied_lectures.class_id=opened_lectures.class_id " +
            "AND applied_lectures.student_id=students.student_id AND opened_lectures.professor_id=#{professor_id} " +
            "AND opened_lectures.lecture_id=#{lecture_id} AND opened_lectures.class_id=#{class_id}")
    @Results(id="StudentsResultSet",value = {
            @Result(property = "student_id", column = "student_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "grade", column = "grade"),
            @Result(property = "birth_date", column = "birth_date")
    })
    List<StudentsDTO> selectALStudentsByProfessor(OpenedLecturesDTO dto);

    @Delete("DELETE FROM applied_lectures WHERE lecture_id=#{lecture_id} " +
            "AND class_id=#{class_id} AND student_id=#{student_id}")
    void deleteAL(AppliedLecturesDTO dto);


    @Insert("INSERT INTO buildings VALUE(#{building_id})")
    void insertBuildings(BuildingsDTO dto);

    @Select("SELECT * FROM buildings")
    @Result(property = "building_id", column = "building_id")
    List<BuildingsDTO> selectBuildingsAll(BuildingsDTO dto);

    @Delete("DELETE FROM buildings WHERE building_id=#{building_id}")
    void deleteBuildings(BuildingsDTO dto);


    @Insert("INSERT INTO lecture_rooms VALUES(#{building_id},#{room_id})")
    void insertLR(LectureRoomsDTO dto);

    @Select("SELECT * FROM lecture_rooms")
    @Results(id = "LectureRoomsResultSet", value = {
            @Result(property = "building_id", column = "building_id"),
            @Result(property = "room_id", column = "room_id")
    })
    List<LectureRoomsDTO> selectLRAll(LectureRoomsDTO dto);

    @Delete("DELETE FROM lecture_rooms WHERE building_id=#{building_id} AND room_id=#{room_id}")
    void deleteLR(LectureRoomsDTO dto);


    @Insert("INSERT INTO opened_lectures VALUES(#{lecture_id},#{class_id},#{professor_id}," +
            "#{building_id},#{room_id},#{schedule},#{applied_students_number},#{capacity}, " +
            "#{can_be_applied},#{can_modify_plan},#{plan})")
    void insertOL(OpenedLecturesDTO dto);

    @Select("SELECT * FROM opened_lectures")
    @Results(id = "OpenedLecturesResultSet", value = {
            @Result(property = "lecture_id", column = "lecture_id"),
            @Result(property = "class_id", column = "class_id"),
            @Result(property = "professor_id", column = "professor_id"),
            @Result(property = "building_id", column = "building_id"),
            @Result(property = "room_id", column = "room_id"),
            @Result(property = "schedule", column = "schedule"),
            @Result(property = "applied_students_number", column = "applied_students_number"),
            @Result(property = "capacity", column = "capacity"),
            @Result(property = "can_be_applied", column = "can_be_applied"),
            @Result(property = "can_modify_plan", column = "can_modify_plan"),
            @Result(property = "plan", column = "plan")
    })
    List<OpenedLecturesDTO> selectOLAll(OpenedLecturesDTO dto);

    @Select("SELECT * FROM opened_lectures WHERE lecture_id=#{lecture_id} AND class_id=#{class_id}")
    @ResultMap("OpenedLecturesResultSet")
    List<OpenedLecturesDTO> selectOLByLectureAndClassId(OpenedLecturesDTO dto);

    @Select("SELECT * from opened_lectures WHERE professor_id=#{professor_id}")
    @ResultMap("OpenedLecturesResultSet")
    List<OpenedLecturesDTO> selectOLByProfessorId(ProfessorsDTO professorsDTO);

    @Select("SELECT opened_lectures.* FROM opened_lectures,lectures " +
            "WHERE opened_lectures.lecture_id=lectures.lecture_id AND target_grade=#{target_grade}")
    @ResultMap("OpenedLecturesResultSet")
    List<OpenedLecturesDTO> selectOLByGrade(int targetGrade);

    @Select("SELECT * FROM opened_lectures WHERE lecture_id=#{lecture_id} AND class_id=#{class_id}")
    List<OpenedLecturesDTO> selectOLPlanByLectureAndClassId(OpenedLecturesDTO openedLecturesDTO );

    @Update("UPDATE opened_lectures SET professor_id=#{professor_id},building_id=#{building_id}," +
            "room_id=#{room_id},schedule=#{schedule},capacity=#{capacity}" +
            "WHERE lecture_id=#{lecture_id} AND class_id=#{class_id}")
    void updateOLAll(OpenedLecturesDTO dto);

    @Update("UPDATE opened_lectures SET professor_id=#{professor_id} WHERE lecture_id=#{lecture_id} AND class_id=#{class_id}")
    void updateOLProfessor(OpenedLecturesDTO dto);

    @Update("UPDATE opened_lectures SET building_id=#{building_id},room_id=#{room_id} " +
            "WHERE lecture_id=#{lecture_id} AND class_id=#{class_id}")
    void updateOLRoom(OpenedLecturesDTO dto);

    @Update("UPDATE opened_lectures SET schedule=#{schedule} WHERE lecture_id=#{lecture_id} AND class_id=#{class_id}")
    void updateOLSchedule(OpenedLecturesDTO dto);

    @Update("UPDATE opened_lectures SET applied_students_number=#{applied_students_number} " +
            "WHERE lecture_id=#{lecture_id} AND class_id=#{class_id}")
    void updateOLAppliedStudentsNum(OpenedLecturesDTO dto);

    @Update("UPDATE opened_lectures SET capacity=#{capacity} " +
            "WHERE lecture_id=#{lecture_id} AND class_id=#{class_id}")
    void updateOLCapacity(OpenedLecturesDTO dto);

    @Update("UPDATE opened_lectures ol INNER JOIN lectures l ON ol.lecture_id=l.lecture_id " +
            "SET can_be_applied=true WHERE l.target_grade=#{target_grade}")
    void updateCanBeAppliedTrue(LecturesDTO dto);

    @Update("UPDATE opened_lectures ol INNER JOIN lectures l ON ol.lecture_id=l.lecture_id " +
            "SET can_be_applied=false WHERE l.target_grade=#{target_grade}")
    void updateCanBeAppliedFalse(LecturesDTO dto);

    @Update("UPDATE opened_lectures SET can_modify_plan=true")
    void updateOLCan_modify_planTrue();

    @Update("UPDATE opened_lectures SET can_modify_plan=false")
    void updateOLCan_modify_planFalse();


    @Select("SELECT * from opened_lectures WHERE professor_id=#{professor_id}")
    @ResultMap("OpenedLecturesResultSet")
    List<OpenedLecturesDTO> selectScheduleByProfessorId(String professorId);


    @Update("UPDATE opened_lectures SET plan=#{plan} WHERE lecture_id=#{lecture_id} AND class_id=#{class_id}")
    void updateOLPlan(OpenedLecturesDTO dto);

    @Delete("DELETE FROM opened_lectures WHERE lecture_id=#{lecture_id} AND class_id=#{class_id}")
    void deleteOL(OpenedLecturesDTO dto);


    @Insert("INSERT INTO periods VALUES(#{grade},#{start_date},#{end_date})")
    void insertPeriods(PeriodsDTO dto);

    @Select("SELECT * FROM periods")
    @Results(id="PeriodsResultSet", value = {
            @Result(property = "id", column="id"),
            @Result(property = "password", column="password"),
            @Result(property = "user_type", column="user_type")
    })
    List<PeriodsDTO> selectPeriodsAll(PeriodsDTO dto);

    @Select("SELECT * FROM periods WHERE grade=#{grade}")
    @ResultMap("PeriodsResultSet")
    List<PeriodsDTO> selectPeriodsByGrade(PeriodsDTO dto);

    @Update("UPDATE periods SET grade=#{grade}, start_date=#{start_date}, end_date=#{end_date} WHERE grade=#{grade}")
    void updatePeriodsDates(PeriodsDTO dto);


    @Insert("INSERT INTO professors VALUES(#{professor_id},#{name},#{phone_num},#{birth_date})")
    void insertProfessors(ProfessorsDTO dto);

    @Select("SELECT * FROM professors")
    List<ProfessorsDTO> selectProfessorsAll(ProfessorsDTO dto);

    @Select("SELECT * FROM professors WHERE professor_id=#{professor_id}")
    List<ProfessorsDTO> selectProfessorById(ProfessorsDTO dto);

    @Update("UPDATE professors SET name=#{name},phone_num=#{phone_num},birth_date=#{birth_date} WHERE professor_id=#{professor_id}")
    void updateProfessorAll(ProfessorsDTO dto);

    @Update("UPDATE professors SET name=#{name} WHERE professor_id=#{professor_id}")
    void updateProfessorName(ProfessorsDTO dto);

    @Update("UPDATE professors SET phone_num=#{phone_num} WHERE professor_id=#{professor_id}")
    void updateProfessorPhoneNum(ProfessorsDTO dto);

    @Update("UPDATE professors SET birth_date=#{birth_date} WHERE professor_id=#{professor_id}")
    void updateProfessorBirthDate(ProfessorsDTO dto);

    @Delete("DELETE FROM professors WHERE professor_id=#{professor_id}")
    void deleteProfessor(ProfessorsDTO dto);


    @Insert("INSERT INTO students VALUES(#{student_id},#{name},#{grade},#{birth_date},#{phone_num})")
    void insertStudent(StudentsDTO dto);

    @Select("SELECT * FROM students")
    List<StudentsDTO> selectStudentsAll(StudentsDTO dto);

    @Select("SELECT * FROM students WHERE student_id=#{student_id}")
    List<StudentsDTO> selectStudentById(StudentsDTO dto);

    @Update("UPDATE students SET name=#{name},grade=#{grade},birth_date=#{birth_date} WHERE student_id=#{student_id}")
    void updateStudentAll(StudentsDTO dto);

    @Update("UPDATE students SET name=#{name} WHERE student_id=#{student_id}")
    void updateStudentName(StudentsDTO dto);

    @Update("UPDATE students SET grade=#{grade} WHERE student_id=#{student_id}")
    void updateStudentGrade(StudentsDTO dto);

    @Update("UPDATE students SET birth_date=#{birth_date} WHERE student_id=#{student_id}")
    void updateStudentBirthDate(StudentsDTO dto);

    @Update("UPDATE students SET phone_num=#{phone_num} WHERE student_id=#{student_id}")
    void updateStudentPhNum(StudentsDTO dto);

    @Delete("DELETE FROM students WHERE student_id=#{student_id}")
    void deleteStudent(StudentsDTO dto);

}